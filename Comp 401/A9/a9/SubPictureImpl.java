package a9;

public class SubPictureImpl extends AnyPicture implements SubPicture {

	private Picture source;
	private int x_offset;
	private int y_offset;
	private int width;
	private int height;
	
	public SubPictureImpl(Picture source, int xoff, int yoff, int width, int height) {
		if (source == null) {
			throw new IllegalArgumentException("Source of subpicture is null");
		}
		
		
		if (xoff < 0 || xoff >= source.getWidth() ||
				yoff < 0 || yoff >= source.getHeight() ||
				width < 1 ||
				xoff+width > source.getWidth() ||
				height < 1 ||
				yoff+height > source.getHeight()) {
			throw new IllegalArgumentException("Subpicture geometry is invalid.");
		}
		
		x_offset = xoff;
		y_offset = yoff;
		this.source = source;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if ((x < 0) || (x >= width) ||  (y < 0) || (y >= height)) {
			throw new IllegalArgumentException("Coordinates out of range.");
		}
		
		return source.getPixel(x+x_offset, y+y_offset);
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		if ((x < 0) || (x >= width) ||  (y < 0) || (y >= height)) {
			throw new IllegalArgumentException("Coordinates out of range.");
		}
		
		if (p == null) {
			throw new IllegalArgumentException("Pixel is null");
		}
		
		source.setPixel(x+x_offset, y+y_offset, p);
	}

	@Override
	public Picture getSource() {
		return source;
	}

	@Override
	public int getXOffset() {
		return x_offset;
	}

	@Override
	public int getYOffset() {
		return y_offset;
	}
}
