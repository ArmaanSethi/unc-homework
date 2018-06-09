package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ZigZagIterator implements Iterator<Pixel>{
	private Picture source;
	private int cur_idx;
	private int cur_idx_x;
	private int cur_idx_y;
	private boolean movingUp;
//	private boolean movingUpRight = false;
//	private boolean movingUpRight = false;

	public ZigZagIterator(Picture source){
		this.source = source;
		cur_idx = 0;
		cur_idx_x = 0;
		cur_idx_y = 0;
		movingUp = true;
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
    	int x = cur_idx_x;
    	int y = cur_idx_y;
    	System.out.println("X: " + x + "  Y: " + y);
	    if (!hasNext()) {
	    	throw new NoSuchElementException("No next song in iteration.");
	    }
	    Pixel p = source.getPixel(x,y);
	    if(movingUp){
		    if((cur_idx_y == 0)&&(cur_idx_x != source.getWidth()-1)){
		    	cur_idx_x++;
		    	movingUp = false;
		    }else if(cur_idx_x == source.getWidth()-1){
		    	cur_idx_y++;
		    	movingUp = false;
		    }else{
		    	cur_idx_x++;
		    	cur_idx_y--;
		    }
	    }else if(!movingUp){
			if(cur_idx_y == source.getHeight()-1){
		    	cur_idx_x++;
		    	movingUp = true;
			}else if(cur_idx_x == 0){
		    	cur_idx_y++;
		    	movingUp = true;
			}else{
				cur_idx_x--;
				cur_idx_y++;
			}
		}
	    
	    cur_idx++;
	    
	    return p;
    }
	
}

