package a4test;

import static org.junit.Assert.*;

import org.junit.Test;

import a4adept.*;

public class A4AdeptTest {

	private static final Pixel RED = new ColorPixel(1,0,0);
	private static final Pixel GREEN = new ColorPixel(0,1,0);
	private static final Pixel BLUE = new ColorPixel(0,0,1);
	private static final Pixel WHITE = new GrayPixel(1);
	private static final Pixel BLACK = new GrayPixel(0);

	@Test
	public void testHorizontalStackPicture() {
		Picture left = new PictureImpl(2,2);
		for (int x=0; x<2; x++) {
			for (int y=0; y<2; y++) {
				left.setPixel(x, y, RED);
			}
		}
		Picture right = new PictureImpl(3,2);
		for (int x=0; x<3; x++) {
			for (int y=0; y<2; y++) {
				right.setPixel(x, y, BLUE);
			}
		}
		Picture p = new HorizontalStackPicture(left, right);
		assertEquals("Horizontal stack picture width is not correct",
			5, p.getWidth());
		assertEquals("Horizontal stack picture height is not correct",
				2, p.getHeight());
		
		assertEquals("Pixel retrieved from left side of horizontal stack not correct",
				RED, p.getPixel(1, 1));
		assertEquals("Pixel retrieved from right side horizontal stack not correct",
				BLUE, p.getPixel(2, 1));
	}

	@Test
	public void testVerticalStackPicture() {
		Picture top = new PictureImpl(2,2);
		for (int x=0; x<2; x++) {
			for (int y=0; y<2; y++) {
				top.setPixel(x, y, RED);
			}
		}
		Picture bottom = new PictureImpl(2,3);
		for (int x=0; x<2; x++) {
			for (int y=0; y<3; y++) {
				bottom.setPixel(x, y, BLUE);
			}
		}
		Picture p = new VerticalStackPicture(top, bottom);
		assertEquals("Vertical stack picture width is not correct",
			2, p.getWidth());
		assertEquals("Vertical stack picture height is not correct",
				5, p.getHeight());
		
		assertEquals("Pixel retrieved from top side of vertical stack not correct",
				RED, p.getPixel(1, 1));
		assertEquals("Pixel retrieved from bottom side of vertical stack not correct",
				BLUE, p.getPixel(1, 2));
	}

	@Test
	public void testStackPictureExceptions() {
		Picture p1 = new PictureImpl(2,2);
		Picture p2 = new PictureImpl(3,3);
		
		try {
			Picture p = new HorizontalStackPicture(p1, p2);
			fail("Expected IllegalArgumentException for mismatched heights of horizontal stack components");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("Expected IllegalArgumentException but got generic RuntimeException");
		}
		
		try {
			Picture p = new VerticalStackPicture(p1, p2);
			fail("Expected IllegalArgumentException for mismatched widths of vertical stack components");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("Expected IllegalArgumentException but got generic RuntimeException");
		}

		try {
			Picture p = new HorizontalStackPicture(p1, null);
			fail("Expected IllegalArgumentException for null horizontal stack component");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("Expected IllegalArgumentException but got generic RuntimeException");
		}

		try {
			Picture p = new HorizontalStackPicture(null, p2);
			fail("Expected IllegalArgumentException for null horizontal stack component");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("Expected IllegalArgumentException but got generic RuntimeException");
		}

		try {
			Picture p = new VerticalStackPicture(p1, null);
			fail("Expected IllegalArgumentException for null vertical stack components");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("Expected IllegalArgumentException but got generic RuntimeException");
		}

		try {
			Picture p = new VerticalStackPicture(null, p2);
			fail("Expected IllegalArgumentException for null vertical stack components");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("Expected IllegalArgumentException but got generic RuntimeException");
		}
	}
	
	@Test
	public void testStackPictureExtract() {
		Picture p1 = createPicture(3,3,RED);
		Picture p2 = createPicture(3,3,BLUE);
		
		Picture hs = new HorizontalStackPicture(p1, p2);
		Picture p = hs.extract(2, 1, 2, 1);
		
		assertEquals("Pixel value from subpicture extracted from stack picture incorrect",
				RED, p.getPixel(0, 0));
		assertEquals("Pixel value from subpicture extracted from stack picture incorrect",
				BLUE, p.getPixel(1, 0));
		
		p1.setPixel(2,1,GREEN);
		p2.setPixel(0, 1, GREEN);

		assertEquals("Pixel value from subpicture extracted from stack picture incorrect",
				GREEN, p.getPixel(0, 0));
		assertEquals("Pixel value from subpicture extracted from stack picture incorrect",
				GREEN, p.getPixel(1, 0));
		
	}
	
	private Picture createPicture(int w, int h, Pixel p) {
		Picture res = new PictureImpl(w,h);
		for (int x=0; x<w; x++) {
			for (int y=0; y<h; y++) {
				res.setPixel(x, y, p);
			}
		}
		return res;
	}
}
