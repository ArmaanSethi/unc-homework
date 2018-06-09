package a7;

public class ObservableRegionImpl implements ObservableRegion{
	private Region r;
	private ROIObserver o;
	public ObservableRegionImpl(ROIObserver o, Region r){
		this.o = o;
		this.r = r;
	}
	//delegate everything
	//include 2 getters
	@Override
	public Coordinate getUpperLeft() {
		return this.r.getUpperLeft();
	}

	@Override
	public Coordinate getLowerRight() {
		return this.r.getLowerRight();
	}

	@Override
	public int getTop() {
		return this.r.getTop();
	}

	@Override
	public int getBottom() {
		return this.r.getBottom();
	}

	@Override
	public int getLeft() {
		return this.r.getLeft();
	}

	@Override
	public int getRight() {
		return this.r.getRight();
	}

	@Override
	public Region intersect(Region other) throws NoIntersectionException {
		return this.r.intersect(other);
	}

	@Override
	public Region union(Region other) {
		return this.r.union(other);
	}
	@Override
	public Region getRegion() {
		return this.r;
	}
	@Override
	public ROIObserver getObserver() {
		return this.o;
	}

}
