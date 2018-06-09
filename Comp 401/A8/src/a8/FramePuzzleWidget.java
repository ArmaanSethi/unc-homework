package a8;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class FramePuzzleWidget extends JPanel implements KeyListener, MouseListener {
	private int height, width;
	ObservablePicture[][] obs_p = new ObservablePicture[5][5];
	PictureView[][] p_view = new PictureView[5][5];
	int x_pos,y_pos;
	ObservablePicture solid_tile;
//	create a 5x5 grid layout with horizontal and vertical gaps in order to differentiate the tiles
//	create 25 tiles that make the original picture with the bottom right tile being a solid color
	public FramePuzzleWidget(Picture p) {
		GridLayout layout = new GridLayout(5, 5);
		layout.setHgap(1);
		layout.setVgap(1);
		setLayout(layout);
		width = p.getWidth();
		height = p.getHeight();
		int sub_w = width / 5;
		int sub_h = height / 5;
		x_pos = 4;
		y_pos = 4;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				ObservablePictureImpl tile = new ObservablePictureImpl(new PictureImpl(sub_w, sub_h));
				if (i == 4 && j == 4) {
					for (int x = 0; x < sub_w; x++) {
						for (int y = 0; y < sub_h; y++) {
							tile.setPixel(x, y, new ColorPixel(0.0, 1.0, 0.0));//set the color of the solid tile
						}
					}
					obs_p[i][j] = tile;
				} else {
					for (int x = 0; x < sub_w; x++) {
						for (int y = 0; y < sub_h; y++) {
							tile.setPixel(x, y, p.getPixel(i * sub_w + x, j * sub_h + y));
						}
					}
					obs_p[i][j] = tile;
				}
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				p_view[j][i] = new PictureView(obs_p[j][i]);
				p_view[j][i].addKeyListener(this);
				p_view[j][i].addMouseListener(this);
				p_view[j][i].setFocusable(true);
				add(p_view[j][i]);
			}
		}
		solid_tile = obs_p[x_pos][y_pos];
	}
//Was not as responsive as mousePressed
	public void mouseClicked(MouseEvent e) {
//		swapTiles((PictureView) e.getSource());
	}
//	Swap Tiles
	public void swap(PictureView view) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (p_view[i][j] == view) {
					if (j == y_pos && i < x_pos) {
						for (int x = x_pos - 1; x >= i; x--) {
							ObservablePicture temporary = p_view[x][j].getPicture();
							p_view[x + 1][j].setPicture(temporary);
						}
						p_view[i][j].setPicture(solid_tile);
						x_pos = i;
						y_pos = j;
					} else if (j == y_pos && i > x_pos) {
						for (int x = x_pos + 1; x <= i; x++) {
							ObservablePicture temporary = p_view[x][j].getPicture();
							p_view[x - 1][j].setPicture(temporary);
						}
						p_view[i][j].setPicture(solid_tile);
						x_pos = i;
						y_pos = j;
					} else if (i == x_pos && j < y_pos) {
						for (int y = y_pos - 1; y >= j; y--) {
							ObservablePicture temporary = p_view[i][y].getPicture();
							p_view[i][y + 1].setPicture(temporary);
						}
						p_view[i][j].setPicture(solid_tile);
						x_pos = i;
						y_pos = j;
					} else if (i == x_pos && j > y_pos) {
						for (int y = y_pos + 1; y <= j; y++) {
							ObservablePicture temporary = p_view[i][y].getPicture();
							p_view[i][y - 1].setPicture(temporary);
						}
						p_view[i][j].setPicture(solid_tile);
						x_pos = i;
						y_pos = j;
					}
				}
			}
		}
	}
//	Read keyboard inputs and swap respective tiles
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		PictureView view = null;
		if ((keyCode == KeyEvent.VK_UP) && (y_pos != 0))
			view = p_view[x_pos][y_pos - 1];
		if ((keyCode == KeyEvent.VK_DOWN) && (y_pos != 4))
			view = p_view[x_pos][y_pos + 1];
		if ((keyCode == KeyEvent.VK_LEFT) && (x_pos != 0))
			view = p_view[x_pos - 1][y_pos];
		if ((keyCode == KeyEvent.VK_RIGHT) && (x_pos != 4))
			view = p_view[x_pos + 1][y_pos];
		swap(view);
	}
//	Read mouse press and swap relevant tiles. Mouse pressed works better than mouse clicked.
	public void mousePressed(MouseEvent e) {
		swap((PictureView) e.getSource());

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
