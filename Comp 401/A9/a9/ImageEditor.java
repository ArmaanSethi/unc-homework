package a9;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
//This is where the initial image editor is created
//Features: Open New Image, Copy Pixel (and Automatically open Paint Brush Tool, Change Brush Size, Change Opacity
public class ImageEditor {
	public static void main(String[] args) throws IOException {
		Picture f = PictureImpl.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");

		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 9 Image Editor - Armaan Sethi");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageEditorModel model = new ImageEditorModel(f);
		ImageEditorView view = new ImageEditorView(main_frame, model);
 		ImageEditorController controller = new ImageEditorController(view, model);
		

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(view, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}
}