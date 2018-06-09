package a6adept;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RowMajorPixelIterator implements Iterator<Pixel>{
	private Picture source;
	private int cur_idx;

	public RowMajorPixelIterator(Picture source){
		this.source = source;
		cur_idx = 0;
	}
	
    public boolean hasNext(){
    	int size = source.getHeight()*source.getWidth();
    	if (cur_idx >= size){
        	return false;
        }else{
        	return true;
        }
    }
    public Pixel next(){
    	int x = cur_idx%source.getWidth();
    	int y = cur_idx/source.getWidth();
	    if (!hasNext()) {
	    	throw new NoSuchElementException("No next song in iteration.");
	    }
	    cur_idx += 1;
	    Pixel p = source.getPixel(x,y);
	    return p;
    }
	
}
