package a3jedi;

public class GrayPixel implements Pixel{
	private double intensity;
	private char character;
	public GrayPixel(double i){
	    if((i > 1)||(i < 0)){
			throw new RuntimeException("i value out of bounds");
		}
		intensity = i;
		if(intensity >= 0.9){
			character = ' ';
		}else if(intensity >= 0.8){
			character = '-';
		}else if(intensity >= 0.7){
			character = ':';
		}else if(intensity >= 0.6){
			character = 's';
		}else if(intensity >= 0.5){
			character = '>';
		}else if(intensity >= 0.4){
			character = '<';
		}else if(intensity >= 0.3){
			character = 'D';
		}else if(intensity >= 0.2){
			character = 'X';
		}else if(intensity >= 0.1){
			character = 'M';
		}else {
			character = '#';
		}

	}
	@Override
	public double getRed() {
		// TODO Auto-generated method stub
		return intensity;
	}

	@Override
	public double getGreen() {
		return intensity;
	}

	@Override
	public double getBlue() {
		return intensity;
	}

	@Override
	public double getIntensity() {
		return intensity;

	}

	@Override
	public char getChar() {
		return character;
	}
	@Override
	public Pixel blend(Pixel p, double weight){
    	if(p == null)
			throw new RuntimeException("Equals Null");
    	if(weight > 1 ||weight <= 0)
			throw new RuntimeException("Equals Null");
	    double new_intensity = intensity*weight + (1-weight)* p.getRed();
	    return new ColorPixel(new_intensity, new_intensity, new_intensity);
	}
	@Override
    public Pixel lighten(double factor){
        Pixel light_pixel = new GrayPixel(1.0);
        return blend(light_pixel,factor);
        
    }
    @Override
    public Pixel darken(double factor){
        Pixel dark_pixel = new GrayPixel(0);
        return blend(dark_pixel,factor);
    }
    @Override
    public boolean equals(Pixel p){
    	if(p == null)
			throw new RuntimeException("Equals Null");
        double error = Math.max(intensity, p.getIntensity())*0.10;
        return((Math.abs(intensity - p.getRed()) < error)&&(Math.abs(intensity) < error)&&(Math.abs(intensity - p.getBlue()) < error));
    }

}
