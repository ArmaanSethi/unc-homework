package a8;

import java.util.List;

import java.util.ArrayList;

public class ObservablePictureImpl implements ObservablePicture {
	Picture picture;
	List<RegisteredROIObserver> observers;
	boolean suspended;
	Region changed_region;

	public ObservablePictureImpl(Picture p) {
		picture = p;
		observers = new ArrayList<RegisteredROIObserver>();
		suspended = false;
		changed_region = null;
	}
	@Override
	public int getWidth() {
		return picture.getWidth();
	}

	@Override
	public int getHeight() {
		return picture.getHeight();	
	}

	@Override
	public Pixel getPixel(int x, int y) {
		return picture.getPixel(x,y);
	}

	@Override
	public Pixel getPixel(Coordinate c) {
		return picture.getPixel(c);
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		setPixel(new Coordinate(x,y), p);
	}

	@Override
	public void setPixel(Coordinate c, Pixel p) {
		picture.setPixel(c, p);

		changed_region = (new RegionImpl(c,c)).union(changed_region);
		notifyObservers();
	}

	@Override
	public SubPicture extract(int xoff, int yoff, int width, int height) {
		return picture.extract(xoff, yoff, width, height);
	}

	@Override
	public SubPicture extract(Coordinate a, Coordinate b) {
		return picture.extract(a, b);
	}

	@Override
	public SubPicture extract(Region r) {
		return picture.extract(r);
	}

	@Override
	public void registerROIObserver(ROIObserver observer, Region r) {
		observers.add(new RegisteredROIObserverImpl(observer, r));
	}

	@Override
	public void registerROIObserver(ROIObserver observer) {
		registerROIObserver(observer, 
				new RegionImpl(new Coordinate(0,0), new Coordinate(getWidth()-1, getHeight()-1)));
	}
	
	@Override
	public void unregisterROIObserver(ROIObserver observer) {
		List<RegisteredROIObserver> still_registered_observers = new ArrayList<RegisteredROIObserver>();
		for (RegisteredROIObserver o : observers) {
			if (o.unwrap() != observer) {
				still_registered_observers.add(o);
			}
		}
		observers = still_registered_observers;
	}


	@Override
	public void suspendObservable() {
		suspended = true;
	}

	@Override
	public void resumeObservable() {
		suspended = false;
		notifyObservers();
	}
	
	@Override
	public ObservablePicture createObservable() {
		return this;
	}

	private void notifyObservers() {
		if (!suspended && changed_region != null) {
			for (RegisteredROIObserver o : observers) {
				try {
					Region intersect = o.getROI().intersect(changed_region);
					o.notify(this, intersect);
				} catch (NoIntersectionException e) {
				}
			}
			changed_region = null;
		}
	}
}
