package a8;

public interface Region {

	Coordinate getUpperLeft();
	Coordinate getLowerRight();

	int getTop();
	int getBottom();
	int getLeft();
	int getRight();

	Region intersect(Region other) throws NoIntersectionException;
	Region union(Region other);

}
