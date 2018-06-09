package a6adept;

public class PictureImpl extends AnyPicture {
	
	public PictureImpl(int w, int h){
	    width = w;
	    height = h;
		pixels = new Pixel[width][height];
		for(int x = 0; x < width; x++){
		    for(int y = 0; y < height; y++){
		        pixels[x][y] = new GrayPixel(0.5);
		    }
		}
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
	public Pixel getPixel(int x, int y){
	    if(inBounds(x,y)){
	        return pixels[x][y];
	    }
		return null;
	}
	public Pixel getPixel(Coordinate c){
	    if(inBounds(c.getX(),c.getY())){
	      return pixels[c.getX()][c.getY()];
	    }
		return null;
	}

}

