package a7;

public interface ObservablePicture extends Picture {
	void registerROIObserver(ROIObserver observer, Region r);
	
	void unregisterROIObservers(Region r);
	void unregisterROIObserver(ROIObserver observer);

	ROIObserver[] findROIObservers(Region r);
	
	void suspendObservable();
	void resumeObservable();
}
