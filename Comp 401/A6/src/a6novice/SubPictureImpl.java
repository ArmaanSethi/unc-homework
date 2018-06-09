package a6novice;

public class SubPictureImpl extends AnyPicture implements SubPicture{
	private Picture source;
	private int xOffSet;
	private int yOffSet;
	public SubPictureImpl(Picture source, int xOffset, int yOffset, int width, int height)
	{
		if(source == null || 
				(!inBounds(xOffset+width, 0, source.getWidth())) ||
				(!inBounds(yOffset+height,0,source.getHeight())))
			throw new IllegalArgumentException("Out of Bounds");
		
		this.source = source;
		this.xOffSet = xOffset;
		this.yOffSet = yOffset;
		this.width = width;
		this.height = height;
	}

	public Picture getSource() {
		return source;
	}
	public Pixel getPixel(int x, int y){
		return source.getPixel(x+this.xOffSet,y+this.yOffSet);
	}
	
    public Pixel getPixel(Coordinate c){
    	return source.getPixel(c.getX() + this.xOffSet,c.getY() + this.yOffSet);
    }

	public void setPixel(int x, int y, Pixel p){
		source.setPixel(x+this.xOffSet,y+this.yOffSet, p);
	}
    public void setPixel(Coordinate c, Pixel p){
//    	if(p == null){
//    		throw new RuntimeException("Null Pixel");
//    	}
//        if(inBounds(c.getX(),c.getY())){
        	source.setPixel(c.getX()+this.xOffSet,c.getY()+this.yOffSet, p);
//        }
    }

	public int getXOffset() {
		return xOffSet;
	}
	public int getYOffset() {
		return yOffSet;
	}

}
