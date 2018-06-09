package a6adept;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SampleIterator implements Iterator<Pixel>{
	private Picture source;
	private int init_x;
	private int init_y;
	private int dx;
	private int dy;
	
	private int cur_idx_x;
	private int cur_idx_y;

	public SampleIterator(Picture source, int init_x, int init_y, int dx, int dy){
		this.source = source;
		this.init_x = init_x;
		this.init_y = init_y;
		this.dx = dx;
		this.dy = dy;
		
		cur_idx_x = init_x;
		cur_idx_y = init_y;
	}
	
    public boolean hasNext(){
    	int size = source.getHeight()*source.getWidth();
    	int cur_pos = cur_idx_x + cur_idx_y * source.getWidth();
    	if (cur_pos >= size){
        	return false;
        }else{
        	return true;
        }
    }
    public Pixel next(){
    	int x = cur_idx_x;
    	int y = cur_idx_y;
    	if (!hasNext()) {
	    	throw new NoSuchElementException("No next song in iteration.");
	    }
    	if(cur_idx_x + dx < source.getWidth()){
    		cur_idx_x += dx;
    	}else{
    		cur_idx_y += dy;
    		cur_idx_x = init_x;
    	}
	    Pixel p = source.getPixel(x,y);
	    return p;
    }
	
}
