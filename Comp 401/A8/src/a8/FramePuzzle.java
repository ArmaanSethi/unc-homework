package a8;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

//Create a 5x5 grid of a picture with the bottom right corner replaced with a solid colored tile
//Clicking a row or column in line with the "blank" tile will result in
//moving the tile to where the click occurred.
//Pressing the arrow keys will should swap the solid tile with an adjacent tile.
public class FramePuzzle {
	public static void main (String[] args) throws IOException{
		Picture f = A8Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");
		FramePuzzleWidget widget = new FramePuzzleWidget(f);

		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 8 Frame Puzzle - Armaan Sethi");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		                                
		JPanel panel = new JPanel();    
		panel.setLayout(new BorderLayout());
		panel.add(widget, BorderLayout.CENTER);
		main_frame.setContentPane(panel);
		                                
		main_frame.pack();              
		main_frame.setVisible(true);    

	}

}
