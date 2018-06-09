package a3novice;

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
		blue = b;
		green = g;
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

}
