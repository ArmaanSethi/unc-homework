package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TileIterator implements Iterator<SubPicture>{
	private Picture source;
	private int tile_width;
	private int tile_height;
	private int cur_idx_x;
	private int cur_idx_y;

	public TileIterator(Picture source, int tile_width, int tile_height){
		this.source = source;
		this.tile_width = tile_width;
		this.tile_height = tile_height;
		cur_idx_x = 0;
		cur_idx_y = 0;
	}
	
    public boolean hasNext(){
    	if((cur_idx_x+tile_width > source.getWidth())|| cur_idx_y+tile_height > source.getHeight()){
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
    	if(cur_idx_x + tile_width < source.getWidth()){
    		cur_idx_x+= tile_width;
    	}else{
    		cur_idx_y+= tile_height;
    		cur_idx_x = 0;
    	}
	    //SubPicture p = new source.extract(x, y, window_width, window_height);
	    SubPicture p = new SubPictureImpl(source, x, y, tile_width, tile_height);
	    return p;
    }
	
}
