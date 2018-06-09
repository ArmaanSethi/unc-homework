package a6novice;

public interface Pixel {

	public double getRed();
	public double getBlue();
	public double getGreen();
	public double getIntensity();
	public char getChar();
	
	public Pixel blend(Pixel p, double weight);
	public Pixel lighten(double factor);
	public Pixel darken(double factor);
	
	public boolean equals(Pixel p);
}
