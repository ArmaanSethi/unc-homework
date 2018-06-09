package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class WindowIterator implements Iterator<SubPicture>{
	private Picture source;
	private int window_width;
	private int window_height;
	private int cur_idx_x;
	private int cur_idx_y;

	public WindowIterator(Picture source, int window_width, int window_height){
		this.source = source;
		this.window_width = window_width;
		this.window_height = window_height;
		cur_idx_x = 0;
		cur_idx_y = 0;
	}
	
    public boolean hasNext(){
    	if((cur_idx_x+window_width > source.getWidth())|| cur_idx_y+window_height > source.getHeight()){
    		return false;
    	}else{
        	return true;
        }
    }
    public SubPicture next(){
    	int x = cur_idx_x;
    	int y = cur_idx_y;
    	if (!hasNext()) {
	    	throw new NoSuchElementException("No next element in iteration.");
	    }
    	if(cur_idx_x + window_width < source.getWidth()){
    		cur_idx_x++;
    	}else{
    		cur_idx_y++;
    		cur_idx_x = 0;
    	}
	    SubPicture p = source.extract(x, y, window_width, window_height);
//	    SubPicture p = new SubPictureImpl(source, x, y, window_width, window_height);
	    return p;
    }
	
}
