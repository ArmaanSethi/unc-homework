package a6adept;

public class GrayPixel implements Pixel {

	private double intensity;

	private static final Pixel WHITE_PIXEL = new GrayPixel(1.0);
	private static final Pixel BLACK_PIXEL = new GrayPixel(0.0);
	private static final char[] PIXEL_CHAR_MAP = {'#', 'M', 'X', 'D', '<', '>', 's', ':', '-', ' ', ' '};


	public GrayPixel(double intensity) {
		if (intensity < 0.0 || intensity > 1.0) {
			throw new RuntimeException("Intensity value out of range");
		}
		this.intensity = intensity;
	}

	@Override
	public double getRed() {
		return intensity;
	}

	@Override
	public double getBlue() {
		return intensity;
	}

	@Override
	public double getGreen() {
		return intensity;
	}

	@Override
	public double getIntensity() {
		return intensity;
	}


	@Override
	public char getChar() {
		return PIXEL_CHAR_MAP[(int) (getIntensity()*10.0)];
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
