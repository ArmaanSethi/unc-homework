package a4jedi;

public class TransformedPicture extends AnyPicture implements Picture{
	private Picture source;
	private PixelTransformation xform;
	public TransformedPicture (Picture source, PixelTransformation xform){
		this.source = source;
		this.xform = xform;
	}
	public Pixel getPixel(int x, int y){
		return xform.transform(source.getPixel(x, y));
	}
	public void setPixel(int x, int y, Pixel p){
		throw new UnsupportedOperationException();
	}
}
