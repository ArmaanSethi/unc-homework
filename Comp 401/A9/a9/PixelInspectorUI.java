package a9;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PixelInspectorUI extends JPanel implements ActionListener{

	private ImageEditorView view;
	private ImageEditorModel model;
	private ImageEditorController controller;
	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_label;
	private JButton copy_button;
	private JButton url_button;
	private JTextField url;
	private Pixel copy_pixel;

	public PixelInspectorUI(ImageEditorView view, ImageEditorModel model, ImageEditorController controller) {
		this.view = view;
		this.model = model;
		this.controller = controller;

		setLayout(new GridLayout(0,1));

		//create a panel that displays values
		JPanel inspector_value_panel = new JPanel();
		inspector_value_panel.setLayout(new GridLayout(0,1));
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_label = new JLabel("(r,g,b)");
		inspector_value_panel.add(x_label);
		inspector_value_panel.add(y_label);
		inspector_value_panel.add(pixel_label);
		
		//create a panel that interacts with the user
		JPanel interactive_panel = new JPanel();
		interactive_panel.setLayout(new GridLayout(0,1));
		copy_button = new JButton("Copy this Pixel");
		copy_button.addActionListener(this);
		interactive_panel.add(copy_button);
		url = new JTextField("Insert picture link here and press the button below");
		url_button = new JButton("Open This Link");
		url_button.addActionListener(this);
		interactive_panel.add(url);
		interactive_panel.add(url_button);
		
		add(inspector_value_panel);
		add(interactive_panel);

	}
	
	public void setInfo(int x, int y, Pixel p) {
		copy_pixel = p;
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_label.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));	
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == copy_button){
			//in case of null pixel try/catch
			try{
				//set crush color to the most recent pixel selected
				controller.getPaintBrushTool().getPaintUI().setBrushColor(copy_pixel);
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, 
						"You just copied a pixel, would you like to open the paint brush tool?", 
						"Copy a Pixel", dialogButton);
				if(dialogResult == 0) {
					view.getToolChooserWidget().getToolChoices().setSelectedItem("Paint Brush");
				} else {
					//don't open the paint brush tool, but still copy the pixel
				} 
	
			}catch(NullPointerException n){
				System.out.println("No Pixel Selected");
			}
		}
		if(e.getSource() == url_button){
			//in case the URL is not valid
			try {
				Picture f = PictureImpl.readFromURL(url.getText());
//				Picture f = PictureImpl.readFromURL("https://s3-us-west-1.amazonaws.com/powr/defaults/image-slider2.jpg");
//				System.out.println("URL is: " + url.getText());
				
				JFrame main_frame = new JFrame();
				main_frame.setTitle("Assignment 9 Image Editor - Armaan Sethi");
				main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				model = new ImageEditorModel(f);
				view = new ImageEditorView(main_frame, model);
		 		controller = new ImageEditorController(view, model);
				
				JPanel top_panel = new JPanel();
				top_panel.setLayout(new BorderLayout());
				top_panel.add(view, BorderLayout.CENTER);
				main_frame.setContentPane(top_panel);

				main_frame.pack();
				main_frame.setVisible(true);
				
			} catch (IOException e1) {
				//invalid url
				System.out.println("Your url was not valid");
			}
		}
		
	}

}
