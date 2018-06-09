package a9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintBrushToolUI extends JPanel implements ChangeListener {
	
	private PaintBrushTool paint_brush_tool;//Paint Brush with adjustable size
	private JSlider red_slider;
	private JSlider green_slider;
	private JSlider blue_slider;
	private JSlider brush_slider;//created a new slider for the PaintBrushTool
	private JSlider opacity_slider;//created a new slider for the opacity
	private PictureView color_preview;
	
	public PaintBrushToolUI(PaintBrushTool brush) {
		paint_brush_tool = brush;
		setLayout(new GridLayout(0,1));
		
		JPanel color_chooser_panel = new JPanel();
		color_chooser_panel.setLayout(new BorderLayout());
		
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
		
		JPanel red_slider_panel = new JPanel();
		JLabel red_label = new JLabel("Red:");
		red_slider_panel.setLayout(new BorderLayout());
		red_slider_panel.add(red_label, BorderLayout.WEST);
		red_slider = new JSlider(0,100);
		red_slider.addChangeListener(this);
		red_slider_panel.add(red_slider, BorderLayout.CENTER);

		JPanel green_slider_panel = new JPanel();
		JLabel green_label = new JLabel("Green:");
		green_slider_panel.setLayout(new BorderLayout());
		green_slider_panel.add(green_label, BorderLayout.WEST);
		green_slider = new JSlider(0,100);
		green_slider.addChangeListener(this);
		green_slider_panel.add(green_slider, BorderLayout.CENTER);

		JPanel blue_slider_panel = new JPanel();
		JLabel blue_label = new JLabel("Blue: ");
		blue_slider_panel.setLayout(new BorderLayout());
		blue_slider_panel.add(blue_label, BorderLayout.WEST);
		blue_slider = new JSlider(0,100);
		blue_slider.addChangeListener(this);
		blue_slider_panel.add(blue_slider, BorderLayout.CENTER);
		
		//Add a new Jpanel for the "Brush Size" feature. Can change the size form 1 to 10.
		JPanel brush_slider_panel = new JPanel();
		JLabel brush_label = new JLabel("Brush Size: ");
		brush_slider_panel.setLayout(new BorderLayout());
		brush_slider_panel.add(brush_label, BorderLayout.WEST);
		brush_slider = new JSlider(1, 10);//range of brush size
		brush_slider.setValue(5);//default brush size
		brush_slider.addChangeListener(this);
		brush_slider_panel.add(brush_slider, BorderLayout.CENTER);

		//Add a new Jpanel for the "Opacity" feature. Can change the opacity form 1 to 10.
		JPanel opacity_slider_panel = new JPanel();
		JLabel opacity_label = new JLabel("Opacity: ");
		opacity_slider_panel.setLayout(new BorderLayout());
		opacity_slider_panel.add(opacity_label, BorderLayout.WEST);
		opacity_slider = new JSlider(0, 100);//range of opacity
		opacity_slider.setValue(30);//default opacity
		opacity_slider.addChangeListener(this);
		opacity_slider_panel.add(opacity_slider, BorderLayout.CENTER);

		// Assumes green label is widest and asks red and blue label
		// to be the same.
		Dimension d = green_label.getPreferredSize();
		red_label.setPreferredSize(d);
		blue_label.setPreferredSize(d);
		
		slider_panel.add(red_slider_panel, BorderLayout.CENTER);
		slider_panel.add(green_slider_panel, BorderLayout.CENTER);
		slider_panel.add(blue_slider_panel, BorderLayout.CENTER);
		slider_panel.add(brush_slider_panel, BorderLayout.CENTER);//add brush_slider to the existing slider_panel
		slider_panel.add(opacity_slider_panel, BorderLayout.CENTER);//add brush_slider to the existing slider_panel

		color_chooser_panel.add(slider_panel, BorderLayout.CENTER);

		color_preview = new PictureView(new ObservablePictureImpl(50,50));
		color_chooser_panel.add(color_preview, BorderLayout.EAST);

		add(color_chooser_panel);
		
		stateChanged(null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		paint_brush_tool.setBrushSize(brush_slider.getValue());//change brush size
		paint_brush_tool.setBrushOpacity((double)opacity_slider.getValue()/100);;//set opacity level on percent scale

		Pixel p = new ColorPixel(red_slider.getValue()/100.0,
				                 green_slider.getValue()/100.0,
				                 blue_slider.getValue()/100.0);
		ObservablePicture preview_frame = color_preview.getPicture();
		preview_frame.suspendObservable();
		for (int x=0; x<preview_frame.getWidth(); x++) {
			for (int y=0; y<preview_frame.getHeight(); y++) {
				preview_frame.setPixel(x, y, p);
			}
		}
		preview_frame.resumeObservable();
	}
	
	public Pixel getBrushColor() {
		return color_preview.getPicture().getPixel(0,0);
	}
	public void setBrushColor(Pixel clicked){
		red_slider.setValue((int)(clicked.getRed() * 100));
		green_slider.setValue((int)(clicked.getGreen() * 100));
		blue_slider.setValue((int)(clicked.getBlue() * 100));
	}

}
