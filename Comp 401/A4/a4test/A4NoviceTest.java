package a4test;

import static org.junit.Assert.*;

import org.junit.Test;

import a4novice.*;

public class A4NoviceTest {

	private static final Pixel RED = new ColorPixel(1,0,0);
	private static final Pixel GREEN = new ColorPixel(0,1,0);
	private static final Pixel BLUE = new ColorPixel(0,0,1);
	private static final Pixel WHITE = new GrayPixel(1);
	private static final Pixel BLACK = new GrayPixel(0);
	
	
	@Test
	public void testSubPictureConstructorBasic() {
		Picture p = new PictureImpl(6,4);
		SubPicture sp = new SubPictureImpl(p, 1, 2, 3, 2);
		
		assertEquals("Result from getSource() does not match source picture",
				p, sp.getSource());
		assertEquals("X offset does not match value given to constructor",
				1, sp.getXOffset());
		assertEquals("Y offset does not match value given to constructor",
				2, sp.getYOffset());
		assertEquals("Width does not match value given to constructor",
				3, sp.getWidth());
		assertEquals("Height does not match value given to constructor",
				2, sp.getHeight());		
	}
	
	@Test
	public void testSubPictureConstructorExceptions() {
		Picture p = new PictureImpl(6,4);
		try {
			SubPicture sp = new SubPictureImpl(p, 7, 2, 1, 1);
			fail("Expected IllegalArgumentException for x offset >= source width");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("Expected IllegalArgumentException but got generic RuntimeException");
		}

		try {
			SubPicture sp = new SubPictureImpl(p, 5, 4, 1, 1);
			fail("Expected IllegalArgumentException for y offset >= source height");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("Expected IllegalArgumentException but got generic RuntimeException");
		}

		try {
			SubPicture sp = new SubPictureImpl(p, 3, 2, 4, 1);
			fail("Expected IllegalArgumentException for x offset + width >= source width");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("Expected IllegalArgumentException but got generic RuntimeException");
		}

		try {
			SubPicture sp = new SubPictureImpl(p, 3, 2, 2, 3);
			fail("Expected IllegalArgumentException for y offset + height >= source height");
		} catch (IllegalArgumentException e) {
		} catch (RuntimeException e) {
			fail("Expected IllegalArgumentException but got generic RuntimeException");
		}

	}

	@Test
	public void testSubPictureImplPixelGettersAndSetters() {
		Picture p = new PictureImpl(3, 3);
		p.setPixel(0, 0, RED);
		p.setPixel(1, 0, RED);
		p.setPixel(2, 0, RED);
		p.setPixel(0, 1, GREEN);
		p.setPixel(1, 1, BLUE);
		p.setPixel(2, 1, WHITE);
		p.setPixel(0, 2, BLACK);
		p.setPixel(1, 2, BLACK);
		p.setPixel(2, 2, RED);
		
		SubPicture sp = new SubPictureImpl(p, 1, 1, 2, 2);
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(1, 1), sp.getPixel(0, 0));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(2, 1), sp.getPixel(1, 0));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(1, 2), sp.getPixel(0, 1));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(2, 2), sp.getPixel(1, 1));
		
		sp.setPixel(0, 0, RED);
		assertEquals("Pixel retrieved after setting does not match expected value",
				RED, sp.getPixel(0, 0));
		assertEquals("Pixel in source not changed after setting through subpicture",
				RED, p.getPixel(0, 0));
		
	}	
	
	@Test
	public void testExtract() {
		Picture p = new PictureImpl(3, 3);
		p.setPixel(0, 0, RED);
		p.setPixel(1, 0, RED);
		p.setPixel(2, 0, RED);
		p.setPixel(0, 1, GREEN);
		p.setPixel(1, 1, BLUE);
		p.setPixel(2, 1, WHITE);
		p.setPixel(0, 2, BLACK);
		p.setPixel(1, 2, BLACK);
		p.setPixel(2, 2, RED);
		
		SubPicture sp = p.extract(1, 1, 2, 2);
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(1, 1), sp.getPixel(0, 0));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(2, 1), sp.getPixel(1, 0));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(1, 2), sp.getPixel(0, 1));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(2, 2), sp.getPixel(1, 1));
		
		SubPicture sp2 = sp.extract(1, 1, 1, 1);
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(2, 2), sp2.getPixel(0, 0));

	}
}
