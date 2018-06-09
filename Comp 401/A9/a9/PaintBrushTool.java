package a9;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PaintBrushTool implements Tool {

	private PaintBrushToolUI ui;
	private ImageEditorView view;
	private ImageEditorModel model;
	private ImageEditorController controller;
	private int brush_size = 5;
	private double opacity = 1;
	
	public PaintBrushTool(ImageEditorView view, ImageEditorModel model, ImageEditorController controller) {
		this.view = view;
		this.model = model;
		this.controller = controller;
		ui = new PaintBrushToolUI(this);
	}
	//change the size of the paint brush
	public void setBrushSize(int size){
		brush_size = size;
	}
	public void setBrushOpacity(double opacity){
		this.opacity = opacity;
	}
	public PaintBrushToolUI getPaintUI(){
		return ui;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), brush_size, opacity);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), brush_size, opacity);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Paint Brush";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

}
