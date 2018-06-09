package a7;

public class RegionImpl implements Region{
	private Coordinate upperLeft;
	private Coordinate lowerRight;
	
	public RegionImpl(Coordinate a, Coordinate b){
		if(a == null || b == null){
			throw new IllegalArgumentException();
		}
		if(a.getX() < b.getX()){//a is left of b
			if(a.getY() < b.getY()){//a is upper of b
				upperLeft = new Coordinate(a.getX(), a.getY());// a;
				lowerRight = new Coordinate(b.getX(), b.getY());//b;
			}else{//a is right of b
				upperLeft = new Coordinate(a.getX(), b.getY());
				lowerRight = new Coordinate(b.getX(), a.getY());
			}
		}else{//b is left of a
			if(b.getY() < a.getY()){//b is upper of a
				upperLeft = new Coordinate(b.getX(), b.getY());//b;
				lowerRight = new Coordinate(a.getX(), a.getY());// a;
			}else{//a is upper of b
				upperLeft = new Coordinate(b.getX(), a.getY());
				lowerRight = new Coordinate(a.getX(), b.getY());
			}
		}
	}
	public Coordinate getUpperLeft() {
		return upperLeft;
	}

	public Coordinate getLowerRight() {
		return lowerRight;
	}

	public int getTop() {
		return upperLeft.getY();
	}

	public int getBottom() {
		return lowerRight.getY();
	}

	public int getLeft() {
		return upperLeft.getX();
	}

	public int getRight() {
		return lowerRight.getX();
	}
//return the biggest smaller rectangle that is included in both
	public Region intersect(Region other) throws NoIntersectionException {
		if(other == null)
			throw new NoIntersectionException();
		if(other.getLeft() > this.getRight())
			throw new NoIntersectionException();
		if(other.getBottom() < this.getTop())
			throw new NoIntersectionException();
		int new_top = 0;
		int new_bottom = 0;
		int new_left = 0;
		int new_right = 0;
		
		if(this.getTop() > other.getTop())//want the least top
			new_top = this.getTop();
		else
			new_top = other.getTop();
		if(this.getBottom() > other.getBottom())//want the greatest bottom
			new_bottom = other.getBottom();
		else
			new_bottom = this.getBottom();
		if(this.getRight() < other.getRight())//want the leftest right
			new_right = this.getRight();
		else
			new_right = other.getRight();
		if(this.getLeft() < other.getLeft())//want the rightest left
			new_left = other.getLeft();
		else
			new_left = this.getLeft();
		
		return new RegionImpl(new Coordinate(new_left, new_top), new Coordinate(new_right, new_bottom));
	}

	//return the sallest bigger rectangle the encompasses both
	public Region union(Region other) {
		if(other == null){
			return this;
		}
		int new_top = 0;
		int new_bottom = 0;
		int new_left = 0;
		int new_right = 0;
		if(this.getTop() > other.getTop()){//want the highest top
			new_top = other.getTop();
		}else{
			new_top = this.getTop();
		}
		if(this.getBottom() > other.getBottom()){//want the lowest bottom
			new_bottom = this.getBottom();
		}else{
			new_bottom = other.getBottom();
		}
		if(this.getRight() < other.getRight())//want the rightest right
		{
			new_right = other.getRight();
		}else{
			new_right = this.getRight();
		}
		if(this.getLeft() < other.getLeft()){//want the leftest left
			new_left = this.getLeft();
		}else{
			new_left = other.getLeft();
		}
		return new RegionImpl(new Coordinate(new_left, new_top), new Coordinate(new_right, new_bottom));
	}

}
