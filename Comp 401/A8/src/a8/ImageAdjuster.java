package a8;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
//This application provides 3 sliders for blur, brightness, and saturation
public class ImageAdjuster {
	public static void main (String[] args) throws IOException{
		Picture p = A8Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");
		ImageAdjusterWidget widget = new ImageAdjusterWidget(p);
                  
                                           
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 8 Image Adjuster - Armaan Sethi");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		                                
		JPanel panel = new JPanel();    
		panel.setLayout(new BorderLayout());
		panel.add(widget, BorderLayout.SOUTH);
		main_frame.setContentPane(panel);
		                                
		main_frame.pack();              
		main_frame.setVisible(true);    
	}

}
