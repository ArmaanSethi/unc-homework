package a4adept;

public class HorizontalStackPicture extends AnyPicture implements Picture{
	private Picture left;
	private Picture right;
	public HorizontalStackPicture(Picture left, Picture right){
		if ((left == null)||(right == null)||(left.getHeight() != right.getHeight())){
			throw new IllegalArgumentException("Wrong Height");
		}
		this.left = left;
		this.right = right;
	}
	public Pixel getPixel(int x, int y){
		if(!inBounds(x,0,left.getWidth()+right.getWidth())){
			throw new IllegalArgumentException("Out of Bounds");
		}
		if((!inBounds(y,0,left.getHeight()))){
			throw new IllegalArgumentException("Out of Bounds");
		}
		if(x < left.getWidth())
		{
			return left.getPixel(x, y);
		}else{
			return right.getPixel(x-left.getWidth(), y);
		}
	}
	public void setPixel(int x, int y, Pixel p){
		if(p == null){
			throw new IllegalArgumentException("P is null");
		}
		if((!inBounds(x,0,left.getWidth()+right.getWidth()))||(!inBounds(y,0,left.getHeight()))){
			throw new IllegalArgumentException("Out of Bounds");
		}
		if(x <= left.getWidth())
		{
			left.setPixel(x, y,p);
		}else{
			right.setPixel(x-left.getWidth(), y,p);
		}
	}
	public int getHeight(){
		return left.getHeight();
	}
	public int getWidth(){
		return left.getWidth()+right.getWidth();
	}


}
