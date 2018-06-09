package a3adept;

public class PictureImpl implements Picture {
	private int width;
	private int height;
	private Pixel[][] pixels;
	
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
    public Pixel getPixel(int x, int y){
        if(inBounds(x,y)){
            return pixels[x][y];
        }
		return null;
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

}

