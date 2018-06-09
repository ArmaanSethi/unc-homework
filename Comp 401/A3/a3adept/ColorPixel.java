package a3adept;

public class ColorPixel implements Pixel {
	private double red;
	private double blue;
	private double green;
	private double intensity;
	private char character;
	public ColorPixel(double r, double g, double b){
		if((r > 1)||(r < 0)){
			throw new RuntimeException("Red value out of bounds");
		}
		if((b > 1)||(b < 0)){
			throw new RuntimeException("Blue value out of bounds");
		}
		if((g > 1)||(g < 0)){
			throw new RuntimeException("Glue value out of bounds");
		}
		red = r;
		green = g;
		blue = b;
		intensity = 0.299 * red + 0.587 * green + 0.114 * blue;
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
		return red;
	}

	@Override
	public double getGreen() {
		return green;
	}

	@Override
	public double getBlue() {
		return blue;
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
    	if(weight > 1 ||weight < 0)
			throw new RuntimeException("Equals Null");

	    double new_red = red*weight + (1-weight)* p.getRed();
	    double new_green = green*weight + (1-weight)* p.getGreen();
	    double new_blue = blue*weight + (1-weight)* p.getBlue();
	    return new ColorPixel(new_red, new_green, new_blue);
	}
	@Override
    public Pixel lighten(double factor){
		if(factor > 1 || factor < 0)
			throw new RuntimeException("Factor out of range");
//    	if(weight >= 1 ||weight <= 0)
//			throw new RuntimeException("Equals Null");
        Pixel light_pixel = new GrayPixel(1.0);
        return blend(light_pixel,1-factor);
        
    }
    @Override
    public Pixel darken(double factor){
		if(factor > 1 || factor < 0)
			throw new RuntimeException("Factor out of range");
        Pixel dark_pixel = new GrayPixel(0);
        return blend(dark_pixel,1-factor);
    }
    @Override
    public boolean equals(Pixel p){
    	if(p == null)
			throw new RuntimeException("Equals Null");
        double error = Math.max(intensity, p.getIntensity())*0.10;
        return(((Math.abs(red - p.getRed()) < error)&&(Math.abs(green - p.getGreen()) < error)&&(Math.abs(blue - p.getBlue()) < error)));
    }
}
