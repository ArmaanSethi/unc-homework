package a4adept;

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
}

