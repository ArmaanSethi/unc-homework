package a4jedi;

public class GammaCorrect implements PixelTransformation{
	private double gamma;
	public GammaCorrect (double gamma){
		if(gamma == 0)
			throw new IllegalArgumentException("Gamma is 0");
		this.gamma = gamma;
	}
	public Pixel transform(Pixel p) {
		if(p == null)
			throw new IllegalArgumentException("P is null");
		
		return new ColorPixel(Math.pow(p.getRed(), (1.0/gamma)),
				Math.pow(p.getGreen(), (1.0/gamma)),
				Math.pow(p.getBlue(), (1.0/gamma)));
	}
}
