package a3novice;

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
		// TODO Auto-generated method stub
		return intensity;
	}

	@Override
	public double getBlue() {
		// TODO Auto-generated method stub
		return intensity;
	}

	@Override
	public double getIntensity() {
		// TODO Auto-generated method stub
		return intensity;

	}

	@Override
	public char getChar() {
		// TODO Auto-generated method stub
		return character;
	}

}
