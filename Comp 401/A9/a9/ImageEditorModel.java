package a9;

public class ImageEditorModel {

	private Picture original;
	private ObservablePicture current;
	
	public ImageEditorModel(Picture f) {
		original = f;
		current = original.copy().createObservable();
	}

	public ObservablePicture getCurrent() {
		return current;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size, double opacity) {
		current.suspendObservable();;
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
					xpos < current.getWidth() &&
					ypos >= 0 &&
					ypos < current.getHeight()) {
					//create new rgb values based on the opacity value
					double red = opacity*brushColor.getRed() + (1 - opacity)*getPixel(xpos,ypos).getRed();
					double green = opacity*brushColor.getGreen() + (1 - opacity)*getPixel(xpos,ypos).getGreen();
					double blue = opacity*brushColor.getBlue() + (1 - opacity)*getPixel(xpos,ypos).getBlue();
					Pixel blendedPixel = new ColorPixel(red,green,blue);
					current.setPixel(xpos, ypos, blendedPixel);
				}
			}
		}
		current.resumeObservable();
	}
}
