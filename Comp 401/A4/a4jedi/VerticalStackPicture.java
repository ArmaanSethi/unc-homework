package a4jedi;

public class VerticalStackPicture extends AnyPicture implements Picture{
	private Picture top;
	private Picture bottom;
	public VerticalStackPicture(Picture top, Picture bottom){
		if((top == null)||(bottom == null)||(top.getWidth() != bottom.getWidth())){
			throw new IllegalArgumentException("Wrong Width");
		}
		this.top = top;
		this.bottom = bottom;
	}
	public Pixel getPixel(int x, int y){
		if((!inBounds(y,0,top.getHeight()+bottom.getHeight()))||(!inBounds(x,0,top.getWidth()))){
			throw new IllegalArgumentException("Out of Bounds");
		}
		if(y < top.getHeight())
		{
			return top.getPixel(x, y);
		}else{
			return bottom.getPixel(x, y-top.getHeight());
		}
	}
	public void setPixel(int x, int y, Pixel p){
		if(p == null)
			throw new IllegalArgumentException("P is null");
		if((!inBounds(y,0,top.getHeight()+bottom.getHeight()))||(!inBounds(x,0,top.getWidth()))){
			throw new IllegalArgumentException("Out of Bounds");
		}
		
		if(y < top.getHeight())
		{
			top.setPixel(x, y,p);
		}else{
			bottom.setPixel(x, y-top.getHeight(),p);
		}
	}
	public int getHeight(){
		return top.getHeight()+bottom.getHeight();
	}
	public int getWidth(){
		return top.getWidth();
	}

}
