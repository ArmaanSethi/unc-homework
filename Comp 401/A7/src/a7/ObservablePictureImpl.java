package a7;

import java.util.ArrayList;

public class ObservablePictureImpl implements ObservablePicture{
	private Picture p;
	ArrayList<ObservableRegionImpl> observers= new ArrayList<ObservableRegionImpl>();
	boolean observing;
    private Region changes;//doesn't do multiple ovservers

	
	public ObservablePictureImpl(Picture p){
		if(p==null){
			throw new IllegalArgumentException();
		}
		this.p = p;
		observing = true;
	}
	//Delagate Stuff
	public int getWidth() {
		return this.p.getWidth();
	}

	public int getHeight() {
		return this.p.getHeight();
	}

	public Pixel getPixel(int x, int y) {
		return this.p.getPixel(x, y);
	}
	public Pixel getPixel(Coordinate c) {
		return this.p.getPixel(c);
	}
	//change pixel and update the changes region
	public void setPixel(int x, int y, Pixel p) {
		this.p.setPixel(x, y, p);
		Coordinate c = new Coordinate(x,y);
        Region r = new RegionImpl(c,c);
        if(observing){
            for(ObservableRegionImpl o:this.observers){
                try{
                    o.getObserver().notify(this,o.getRegion().intersect(r));
                }catch (NoIntersectionException e){
                   
                }
            }
        } else{
            changes = r.union(changes);
        }

	}

	public void setPixel(Coordinate c, Pixel p) {
		this.p.setPixel(c, p);
        Region r = new RegionImpl(c,c);
        if(observing){
            for(ObservableRegionImpl o:this.observers){
                try{
                    o.getObserver().notify(this,o.getRegion().intersect(r));
                }catch (NoIntersectionException e){
                   
                }
            }
        } else{
            changes = r.union(changes);
        }

	}
//delagate more stuff
	public SubPicture extract(int xoff, int yoff, int width, int height) {
		return this.p.extract(xoff, yoff, width, height);
	}

	public SubPicture extract(Coordinate a, Coordinate b) {
		return this.p.extract(a, b);
	}

	public void registerROIObserver(ROIObserver observer, Region r) {
		if((observer == null)||(r == null))
			throw new IllegalArgumentException();
		
		observers.add(new ObservableRegionImpl(observer, r));
	}

	public void unregisterROIObservers(Region r) {
		if(r == null){
			throw new IllegalArgumentException();
		}
//		int numOfRemovals = 0;
		for(int i = 0; i < observers.size(); ++i){
			try {
				if(observers.get(i).intersect(r)!= null){
					observers.remove(i);
					i--;
//					numOfRemovals++;
				}
			} catch (NoIntersectionException e) {
				// TODO Auto-generated catch block
				
			}
		}		
//		if(numOfRemovals == 0){
//			throw new IllegalArgumentException();
//		}
//
	}

	public void unregisterROIObserver(ROIObserver observer) {
		if(observer == null){
			throw new IllegalArgumentException();
		}
//		int numOfRemovals = 0;
		for(int i = 0; i < observers.size(); ++i){
			if(observers.get(i).getObserver().equals(observer)){
				observers.remove(i);
				i--;
//				numOfRemovals++;
			}
		}		
//		if(numOfRemovals == 0){
//			throw new IllegalArgumentException();
//		}
	}

	public ROIObserver[] findROIObservers(Region r) {
		if(r == null){
			throw new IllegalArgumentException();
		}
		int numOfRemovals = 0;
		ArrayList<ROIObserver> findArray= new ArrayList<ROIObserver>();
		for(int i = 0; i < observers.size(); ++i){
			if(observers.get(i).getRegion().equals(r)){
				findArray.add((ROIObserver) observers.get(i));
				numOfRemovals++;
			}
		}		
		if(numOfRemovals == 0){
			return new ROIObserver[0];
		}
		return (ROIObserver[]) findArray.toArray();
	}

	public void suspendObservable() {
		observing = false;
	}

	public void resumeObservable() {
		for(int i = 0; i < observers.size(); ++i){
            try{
                Region intersection = observers.get(i).getRegion().intersect(changes);
                observers.get(i).getObserver().notify(this, intersection);
            }catch (NoIntersectionException e){
               
            }
        }
        changes = null;
		observing = true;
	}

}

