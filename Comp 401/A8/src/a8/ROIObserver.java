package a8;

public interface ROIObserver {
	
	void notify(ObservablePicture picture, Region changed_region);
}
