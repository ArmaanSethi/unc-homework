package a6jedi;

public interface TransparentPixel extends Pixel {

	public double getTransparency();
	public TransparentPixel blend(TransparentPixel p, double weight);
}
