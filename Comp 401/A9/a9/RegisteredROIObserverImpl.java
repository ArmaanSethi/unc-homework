package a9;

public class RegisteredROIObserverImpl implements RegisteredROIObserver {

	private ROIObserver observer;
	private Region roi;
	
	public RegisteredROIObserverImpl(ROIObserver o, Region r) {
		observer = o;
		roi = r;
	}
	
	@Override
	public void notify(ObservablePicture frame, Region r) {
		observer.notify(frame, r);
	}

	@Override
	public Region getROI() {
		return roi;
	}

	@Override
	public ROIObserver getObserver() {
		return observer;
	}

}
