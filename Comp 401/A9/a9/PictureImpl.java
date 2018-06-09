package a9;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class PictureImpl extends AnyPicture {

	private Pixel[][] pixels;
	
	
	public PictureImpl(int width, int height) {
		this(width, height, new ColorPixel(0,0,0));
	}
	
	public PictureImpl(int width, int height, Pixel init_color) {
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException("Width or height is illegal");
		}
		if (init_color == null) {
			throw new IllegalArgumentException("Initial pixel is null");
		}
		
		pixels = new Pixel[width][height];
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				pixels[x][y] = init_color;
			}
		}
	}
		
	@Override
	public int getWidth() {
		return pixels.length;
	}

	@Override
	public int getHeight() {
		return pixels[0].length;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("Coordinates out of range");
		}
		return pixels[x][y];
	}
	
	@Override
	public void setPixel(int x, int y, Pixel p) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("Coordinates out of range");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel value is null");
		}
		pixels[x][y] = p;
	}
	
	public static PictureImpl readFromURL(String url) throws IOException {
		BufferedImage bi = ImageIO.read(new URL(url));
		PictureImpl picture = new PictureImpl(bi.getWidth(), bi.getHeight());
		for (int x=0; x<bi.getWidth(); x++) {
			for (int y=0; y<bi.getHeight(); y++) {
				picture.setPixel(x, y, ColorPixel.fromRGB(bi.getRGB(x, y)));
			}
		}
		return picture;
	}

}
