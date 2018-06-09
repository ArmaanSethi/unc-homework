package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageAdjusterWidget extends JPanel implements ChangeListener {
	private Picture source;
	private PictureView view;
	private JSlider blur_slider, brightness_slider, saturation_slider;

//	Create the blur slider, brightness slider, and saturation slider under the picture
	public ImageAdjusterWidget(Picture p) {
		source = p;
		setLayout(new BorderLayout());

		view = new PictureView(p.createObservable());
		add(view, BorderLayout.CENTER);

		JPanel south_panel = new JPanel();
		south_panel.setLayout(new GridLayout(3, 2));
		add(south_panel, BorderLayout.SOUTH);

		blur_slider = new JSlider(0, 5, 0);
		blur_slider.setPaintTicks(true);
		blur_slider.setPaintLabels(true);
		blur_slider.setMajorTickSpacing(1);

		brightness_slider = new JSlider(-100, 100, 0);
		brightness_slider.setPaintTicks(true);
		brightness_slider.setPaintLabels(true);
		brightness_slider.setMajorTickSpacing(25);

		saturation_slider = new JSlider(-100, 100, 0);
		saturation_slider.setPaintTicks(true);
		saturation_slider.setPaintLabels(true);
		saturation_slider.setMajorTickSpacing(25);

		south_panel.add(new JLabel("Blur: "));
		south_panel.add(blur_slider);
		south_panel.add(new JLabel("Saturation: "));
		south_panel.add(saturation_slider);
		south_panel.add(new JLabel("Brightness: "));
		south_panel.add(brightness_slider);

		blur_slider.addChangeListener(this);
		saturation_slider.addChangeListener(this);
		brightness_slider.addChangeListener(this);
	}
//	adjust the blur by averaging the pixels around every pixel
	public void adjustBlur(int x, int y, ObservablePicture temporaryPicture) {
		int blur_value = blur_slider.getValue();
		int counter = 0;
		double sum_r = 0, sum_g = 0, sum_b = 0;
		double mean_r, mean_g, mean_b;
		int min_x = Math.max(0, x - blur_value);
		int max_x = Math.min(source.getWidth() - 1, x + blur_value);
		int min_y = Math.max(0, y - blur_value);
		int max_y = Math.min(source.getHeight() - 1, y + blur_value);
		for (int i = min_x; i <= max_x; i++) {
			for (int j = min_y; j <= max_y; j++) {
				sum_r = sum_r + source.getPixel(i, j).getRed();
				sum_g = sum_g + source.getPixel(i, j).getGreen();
				sum_b = sum_b + source.getPixel(i, j).getBlue();
				counter++;
			}
		}
		if (counter > 0) {
			mean_r = sum_r / counter;
			mean_g = sum_g / counter;
			mean_b = sum_b / counter;
		} else {
			mean_r = source.getPixel(x, y).getRed();
			mean_g = source.getPixel(x, y).getGreen();
			mean_b = source.getPixel(x, y).getBlue();
		}
		temporaryPicture.setPixel(x, y, new ColorPixel(mean_r, mean_g, mean_b));
	}
//	Adjust the brightness of the picture, ranges from -100(black) to 0 (original) to 100(white)
	public void adjustBrightness(int x, int y, ObservablePicture temporaryPicture) {
		int brightness_value = brightness_slider.getValue();
		if (brightness_value > 0) {
			double red = temporaryPicture.getPixel(x, y).getRed() * (1.0 - brightness_value / 100.0)
					+ brightness_value / 100.0;
			double green = temporaryPicture.getPixel(x, y).getGreen() * (1.0 - brightness_value / 100.0)
					+ brightness_value / 100.0;
			double blue = temporaryPicture.getPixel(x, y).getBlue() * (1.0 - brightness_value / 100.0)
					+ brightness_value / 100.0;

			temporaryPicture.setPixel(x, y, new ColorPixel(red, green, blue));
		} else {
			double red = (1 + brightness_value / 100.0) * temporaryPicture.getPixel(x, y).getRed();
			double green = (1 + brightness_value / 100.0) * temporaryPicture.getPixel(x, y).getGreen();
			double blue = (1 + brightness_value / 100.0) * temporaryPicture.getPixel(x, y).getBlue();
			temporaryPicture.setPixel(x, y, new ColorPixel(red, green, blue));
		}
	}
//	adjust the saturation of the picture, ranges from -100(no color) to 0 (original color) 
//	to 100 (fully saturated)
	public void adjustSaturation(int x, int y, ObservablePicture temporaryPicture) {
		int saturation_value = saturation_slider.getValue();

		if (saturation_value <= 0) {
			double red = temporaryPicture.getPixel(x, y).getRed() * (1.0 + saturation_value / 100.0)
					- (temporaryPicture.getPixel(x, y).getIntensity() * saturation_value / 100);
			double green = temporaryPicture.getPixel(x, y).getGreen() * (1.0 + saturation_value / 100.0)
					- (temporaryPicture.getPixel(x, y).getIntensity() * saturation_value / 100);
			double blue = temporaryPicture.getPixel(x, y).getBlue() * (1.0 + saturation_value / 100.0)
					- (temporaryPicture.getPixel(x, y).getIntensity() * saturation_value / 100);
			temporaryPicture.setPixel(x, y, new ColorPixel(red, green, blue));
		} else if (saturation_value > 0) {
			double maxcomp = Math.max(temporaryPicture.getPixel(x, y).getBlue(),
					Math.max(temporaryPicture.getPixel(x, y).getGreen(), temporaryPicture.getPixel(x, y).getRed()));
			double gain = (maxcomp + ((1.0 - maxcomp) * (saturation_value / 100.0)) / maxcomp);
			double red = temporaryPicture.getPixel(x, y).getRed() * gain;
			double green = temporaryPicture.getPixel(x, y).getGreen() * gain;
			double blue = temporaryPicture.getPixel(x, y).getBlue() * gain;

			temporaryPicture.setPixel(x, y, new ColorPixel(red, green, blue));
		}

	}
//	Create a temporary picture and then adjust the blur, brightness, and saturation of it (in that order)
//	Set the Picture View to the temporary picture
	public void stateChanged(ChangeEvent e) {
		// Needed so that the original values are not changed making it
		// impossible to revert to the original
		ObservablePicture temporaryPicture = new ObservablePictureImpl(
				new PictureImpl(source.getWidth(), source.getHeight()));

		for (int x = 0; x < source.getWidth(); x++) {
			for (int y = 0; y < source.getHeight(); y++) {
				adjustBlur(x, y, temporaryPicture);
				adjustBrightness(x, y, temporaryPicture);
				adjustSaturation(x, y, temporaryPicture);
			}
		}
		view.setPicture(temporaryPicture);

	}

}
