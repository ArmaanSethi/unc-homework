package a9;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PictureView extends Canvas implements ROIObserver {

	private ObservablePicture frame;
	private BufferedImage buffered_image;
	
	public PictureView(ObservablePicture f) {
		setPicture(f);
	}

	public void setPicture(ObservablePicture f) {
		if (frame == f) {
			return;
		}
		
		if (frame != null) {
			frame.unregisterROIObserver(this);
		}
		
		frame = f;
		frame.registerROIObserver(this);
		buffered_image = new BufferedImage(f.getWidth(), f.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.setPreferredSize(new Dimension(f.getWidth(), f.getHeight()));
		this.setSize(new Dimension(f.getWidth(), f.getHeight()));
		notify(frame, new RegionImpl(new Coordinate(0,0), new Coordinate(f.getWidth()-1,f.getHeight()-1)));		
	}
	
	public ObservablePicture getPicture() {
		return frame;
	}
	
	public void paint(Graphics g) {
		g.drawImage(buffered_image, 0, 0, this);
	}

	public void notify(ObservablePicture frame, Region area) {
		for (int x=area.getLeft(); x<=area.getRight(); x++) {
			for (int y=area.getTop(); y<=area.getBottom(); y++) {
				buffered_image.setRGB(x, y, frame.getPixel(x, y).toRGB());
			}
		}
		repaint();
	}
}
