package a9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObservablePictureImpl extends AnyPicture implements ObservablePicture {

	private Picture picture;
	private List<RegisteredROIObserver> observers;

	private boolean suspended;
	protected Region changed_region;

	public ObservablePictureImpl(int width, int height) {
		this(width, height, new ColorPixel(0,0,0));
	}

	public ObservablePictureImpl(int width, int height, Pixel init_pixel) {
		this(new PictureImpl(width, height, init_pixel));
	}

	public ObservablePictureImpl(Picture p) {
		if (p == null) {
			throw new IllegalArgumentException("Picture is null");
		}
		this.picture = p;
		observers = new ArrayList<RegisteredROIObserver>();
		suspended = false;
		changed_region = null;
	}

	@Override
	public void registerROIObserver(ROIObserver observer) {
		registerROIObserver(observer, new RegionImpl(new Coordinate(0, 0), new Coordinate(getWidth()-1, getHeight()-1)));
	}

	@Override
	public void registerROIObserver(ROIObserver observer, Region r) {
		if (r == null) {
			throw new IllegalArgumentException("Region to observe is null");
		}
		try {
			Region roi = r.intersect(new RegionImpl(new Coordinate(0, 0), new Coordinate(getWidth()-1, getHeight()-1)));
			observers.add(new RegisteredROIObserverImpl(observer, roi));
		} catch (NoIntersectionException e) {
		}
	}

	@Override
	public void unregisterROIObserver(ROIObserver observer) {
		Iterator<RegisteredROIObserver> roi_iterator = observers.iterator();
		while (roi_iterator.hasNext()) {
			RegisteredROIObserver o = roi_iterator.next();
			if (o.getObserver() == observer) {
				roi_iterator.remove();
			}
		}
	}

	@Override
	public void suspendObservable() {
		suspended = true;
	}

	@Override
	public void resumeObservable() {
		suspended = false;
		notify_observers();
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
	public void setPixel(int x, int y, Pixel p) {
		picture.setPixel(x,y,p);

		changed_region = (new RegionImpl(new Coordinate(x,y), new Coordinate(x,y))).union(changed_region);
		notify_observers();
	}

	protected void notify_observers() {
		if (!suspended) {
			if (changed_region != null) {
				for (RegisteredROIObserver o : observers) {
					try {
						Region intersect = changed_region.intersect(o.getROI());
						o.notify(this, intersect);
					} catch (NoIntersectionException e) {
					}
				}
			}
			changed_region = null;
		}
	}
}
