package a8;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

//This application should load an image from a URL and display information about the pixel where a click occurred. 
public class PixelInspector {
	public static void main(String[] args) throws IOException {
		Picture p = A8Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");
		PixelInspectorWidget widget = new PixelInspectorWidget(p);

		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 8 Pixel Inspector - Armaan Sethi");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(widget, BorderLayout.CENTER);
		main_frame.setContentPane(panel);

		main_frame.pack();
		main_frame.setVisible(true);

	}

}
