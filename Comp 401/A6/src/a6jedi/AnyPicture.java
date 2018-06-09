package a6jedi;

import java.util.Iterator;

public abstract class AnyPicture implements Picture{

	protected int width;
	protected int height;
	protected Pixel[][] pixels;
	
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setPixel(int x, int y, Pixel p){
    	if(p == null){
    		throw new RuntimeException("Null Pixel");
    	}
        if(inBounds(x,y)){
            pixels[x][y] = p;
        }
    }
    public void setPixel(Coordinate c, Pixel p){
    	if(p == null){
    		throw new RuntimeException("Null Pixel");
    	}
        if(inBounds(c.getX(),c.getY())){
            pixels[c.getX()][c.getY()] = p;
        }
    }

    public int countRange(double low, double high){
        int count = 0;
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if((pixels[x][y].getIntensity() <=high)&&(pixels[x][y].getIntensity() >= low)){
                    count++;
                }
            }
        }
        return count;
    }
    public void print(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                System.out.print(pixels[x][y].getChar());
            }System.out.println();
        }
        
    }
	public double unequalPixelRatio(Picture p) {
		// TODO Auto-generated method stub
		if(p == null)
			throw new RuntimeException("Picture is null");
		int count = 0;
		if((width != p.getWidth() || height != p.getHeight()))
            throw new RuntimeException("Wrong Dimensions");
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				if(pixels[x][y].equals(p.getPixel(x, y))){
					count++;
				}
			}
		}
		return 1.0-(double)count/(width*height);
	}
	
	public double meanSquaredError(Picture p) {
		// TODO Auto-generated method stub
		double sum = 0;
		if(p == null)
			throw new RuntimeException("Picture is null");
		if((width != p.getWidth() || height != p.getHeight()))
            throw new RuntimeException("Wrong Dimensions");
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				sum += (pixels[x][y].getIntensity() - (p.getPixel(x, y).getIntensity()))*(pixels[x][y].getIntensity() - (p.getPixel(x, y).getIntensity()));
			}
		}
		return (double)sum/(width*height);
	}
	public double calculatePSNR(Picture p) {
		// TODO Auto-generated method stub
		if(p == null)
			throw new RuntimeException("Picture is null");
		if(equals(p))
			throw new RuntimeException("Picture is the same");
		return 20*Math.log10(1.0) - 10*Math.log10(meanSquaredError(p));		
	}
	public boolean inBounds(int x, int y)
	{
	   if((x > width)||(x < 0)){
            throw new RuntimeException("Not in width");
            //return false;
        }else if((y > height)||(y < 0)){
            throw new RuntimeException("Not in height");
            //return false;
        }else{
            return true;
        }
	}
	boolean inBounds(int num, int lower, int higher){
	if((num >= lower)&&(num <= higher))
		return true;
	else
		return false;
	}

    public SubPicture extract(int xOffset, int yOffset, int width, int height){
    	return (SubPicture) new SubPictureImpl(this, xOffset, yOffset, width, height);
    }
    public SubPicture extract(Coordinate corner_a, Coordinate corner_b){
    	return (SubPicture) new SubPictureImpl(this, corner_a.getX(), corner_a.getY(), corner_b.getX()-corner_a.getX(), corner_b.getY()-corner_a.getY());
    }
    public Iterator<Pixel> iterator(){
    	return new RowMajorPixelIterator(this);
    }
	public Iterator<Pixel> sample(int init_x, int init_y, int dx, int dy){
		return new SampleIterator(this, init_x, init_y, dx, dy);
	}
	public Iterator<SubPicture> window(int window_width, int window_height){
		return new WindowIterator(this, window_width, window_height);
	}
	public Iterator<SubPicture> tile(int tile_width, int tile_height){
		return new TileIterator(this, tile_width, tile_height);
	}
	public Iterator<Pixel> zigzag(){
		return new ZigZagIterator(this);
	}


}
