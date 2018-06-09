package a8;

public class RegionImpl implements Region {

	Coordinate upper_left;
	Coordinate lower_right;
	
	public RegionImpl(Coordinate a, Coordinate b) {
		if (a == null || b == null) {
			throw new IllegalArgumentException("One or both coordinates are null");
		}
		int min_x = a.getX() < b.getX() ? a.getX() : b.getX();
		int min_y = a.getY() < b.getY() ? a.getY() : b.getY();
		int max_x = a.getX() > b.getX() ? a.getX() : b.getX();
		int max_y = a.getY() > b.getY() ? a.getY() : b.getY();

		upper_left = new Coordinate(min_x, min_y);
		lower_right = new Coordinate(max_x, max_y);
	}

	@Override
	public Coordinate getUpperLeft() {
		return upper_left;
	}

	@Override
	public Coordinate getLowerRight() {
		return lower_right;
	}

	@Override
	public int getTop() {
		return upper_left.getY();
	}

	@Override
	public int getBottom() {
		return lower_right.getY();
	}

	@Override
	public int getLeft() {
		return upper_left.getX();
	}

	@Override
	public int getRight() {
		return lower_right.getX();
	}

	@Override
	public Region intersect(Region other) throws NoIntersectionException {
		if ((other == null) ||
		    (other.getLeft() > getRight()) ||
			(other.getRight() < getLeft()) ||
			(other.getTop() > getBottom()) ||
			(other.getBottom() < getTop())) {
			throw new NoIntersectionException();
		}
		int max_left = other.getLeft() > getLeft() ? other.getLeft() : getLeft();
		int min_right = other.getRight() < getRight() ? other.getRight() : getRight();
		int max_top = other.getTop() > getTop() ? other.getTop() : getTop();
		int min_bottom = other.getBottom() < getBottom() ? other.getBottom() : getBottom();
		
		return new RegionImpl(new Coordinate(max_left, max_top), new Coordinate(min_right, min_bottom));
	}

	@Override
	public Region union(Region other) {
		if (other == null) {
			return this;
		}
		
		int min_left = other.getLeft() < getLeft() ? other.getLeft() : getLeft();
		int max_right = other.getRight() > getRight() ? other.getRight() : getRight();
		int min_top = other.getTop() < getTop() ? other.getTop() : getTop();
		int max_bottom = other.getBottom() > getBottom() ? other.getBottom() : getBottom();
		return new RegionImpl(new Coordinate(min_left, min_top), new Coordinate(max_right, max_bottom));
	}
}
