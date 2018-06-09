package a4adept;

public class ColorPixel implements Pixel {

	private double red;
	private double green;
	private double blue;
	
	private static final double RED_INTENSITY_FACTOR = 0.299;
	private static final double GREEN_INTENSITY_FACTOR = 0.587;
	private static final double BLUE_INTENSITY_FACTOR = 0.114;

	private static final Pixel WHITE_PIXEL = new GrayPixel(1.0);
	private static final Pixel BLACK_PIXEL = new GrayPixel(0.0);
	private static final char[] PIXEL_CHAR_MAP = {'#', 'M', 'X', 'D', '<', '>', 's', ':', '-', ' ', ' '};
	
	public ColorPixel(double r, double g, double b) {
		if (r > 1.0 || r < 0.0) {
			throw new RuntimeException("Red out of bounds");
		}
		if (g > 1.0 || g < 0.0) {
			throw new RuntimeException("Green out of bounds");
		}
		if (b > 1.0 || b < 0.0) {
			throw new RuntimeException("Blue out of bounds");
		}
		red = r;
		green = g;
		blue = b;
	}
	
	@Override
	public double getRed() {
		return red;
	}

	@Override
	public double getBlue() {
		return blue;
	}

	@Override
	public double getGreen() {
		return green;
	}

	@Override
	public double getIntensity() {
		return RED_INTENSITY_FACTOR*getRed() + 
				GREEN_INTENSITY_FACTOR*getGreen() + 
				BLUE_INTENSITY_FACTOR*getBlue();
	}
	
	@Override
	public char getChar() {
		int char_idx = (int) (getIntensity()*10.0);
		return PIXEL_CHAR_MAP[char_idx];
	}

	@Override
	public Pixel lighten(double factor) {
		if (factor < 0.0 || factor > 1.0) {
			throw new RuntimeException("Lighten factor out of range");
		}
		return WHITE_PIXEL.blend(this, factor);
	}

	@Override
	public Pixel darken(double factor) {
		if (factor < 0.0 || factor > 1.0) {
			throw new RuntimeException("Darken factor out of range");
		}
		return BLACK_PIXEL.blend(this, factor);
	}

	@Override
	public Pixel blend(Pixel p, double weight) {
		if (weight < 0.0 || weight > 1.0) {
			throw new RuntimeException("Blend weight out of range");
		}
		if (p == null) {
			throw new RuntimeException("Blend pixel is null");
		}
		
		return new ColorPixel(getRed()*weight + p.getRed()*(1.0-weight),
				getGreen()*weight + p.getGreen()*(1.0-weight),
				getBlue()*weight + p.getBlue()*(1.0-weight));
	}
	
	@Override
	public boolean equals(Pixel p) {
		if (p == null) {
			throw new RuntimeException("Pixel passed to equals method is null");
		}
		
		double max_intensity = getIntensity() > p.getIntensity() ? getIntensity() : p.getIntensity();
		double equal_bound = max_intensity * 0.10;
		return ((Math.abs(getRed() - p.getRed()) < equal_bound) &&
				(Math.abs(getGreen() - p.getGreen()) < equal_bound) &&
				(Math.abs(getBlue() - p.getBlue()) < equal_bound));
	}

}
