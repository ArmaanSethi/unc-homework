package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorWidget extends JPanel implements MouseListener {
	
	private PictureView view;
	public JLabel red, green, blue,  brightness, x, y;
	
//	Create a Pixel Inspector Widget with labels for 
//	red, green, blue, brightness, x, and y on the left
	public 	PixelInspectorWidget(Picture p) {
		setLayout(new BorderLayout());
		view = new PictureView(p.createObservable());
		view.addMouseListener(this);
		add(view, BorderLayout.CENTER);
		JPanel west_panel = new JPanel();
		west_panel.setLayout(new GridLayout(6,1));
		add(west_panel, BorderLayout.WEST);
		red= new JLabel("Red:        ");
		green = new JLabel("Green:      ");
		blue= new JLabel ("Blue:       ");
		brightness = new JLabel("Brightness: ");
		x= new JLabel("X:         ");
		y= new JLabel("Y:         ");
		green.addMouseListener(this);
		red.addMouseListener(this);
		blue.addMouseListener(this);
		brightness.addMouseListener(this);
		x.addMouseListener(this);
		y.addMouseListener(this);
		west_panel.add(x);
		west_panel.add(y);
		west_panel.add(red);
		west_panel.add(green);
		west_panel.add(blue);
		west_panel.add(brightness);
	}
//		When the mouse is clicked find get all values relavent to the pixel and display it.
	public void mouseClicked(MouseEvent e) {
		Pixel p = view.getPicture().getPixel(e.getX(),e.getY());
		red.setText("Red:       " + new DecimalFormat("##.##").format(p.getRed()));
		green.setText("Green:      " + new DecimalFormat("##.##").format(p.getGreen()));
		blue.setText("Blue:       "+new DecimalFormat("##.##").format(p.getBlue()));
		brightness.setText("Brightness: "+new DecimalFormat("##.##").format(p.getIntensity()));
		x.setText("X:         " + e.getX());
		y.setText("Y:         " + e.getY());
	}
//	There is no need to override any of the functions we are not using.
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
