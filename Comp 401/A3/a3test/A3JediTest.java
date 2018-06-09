package a3test;

import static org.junit.Assert.*;

import org.junit.Test;

import a3jedi.GrayPixel;
import a3jedi.Picture;
import a3jedi.PictureImpl;
import a3jedi.ColorPixel;
import a3jedi.Pixel;

public class A3JediTest {

	@Test
	public void sizeMismatchTest() {
		Picture p1 = new PictureImpl(3,4);
		Picture p2 = new PictureImpl(4,3);
		
		try {
			double result = p1.calculatePSNR(p2);
			fail("Expected runtime exception for mismatched picture sizes");
		} catch (RuntimeException e) {
		}
		
		try {
			double result = p1.unequalPixelRatio(p2);
			fail("Expected runtime exception for mismatched picture sizes");
		} catch (RuntimeException e) {
		}
	}
	
	@Test
	public void nullPictureTest() {
		Picture p = new PictureImpl(1,1);
		
		try {
			double result = p.calculatePSNR(null);
			fail("Expcected runtime exception for null parameter");
		} catch (RuntimeException e) {
		}

		try {
			double result = p.unequalPixelRatio(null);
			fail("Expcected runtime exception for null parameter");
		} catch (RuntimeException e) {
		}
	}
	
	@Test
	public void psnrTest() {
		Picture p1 = new PictureImpl(1,1);
		Picture p2 = new PictureImpl(1,1);
		
		p1.setPixel(0, 0, new GrayPixel(0.0));
		p2.setPixel(0, 0, new GrayPixel(1.0));

		assertEquals(0.0, p1.calculatePSNR(p2), 0.001);
		
		p2.setPixel(0, 0, new GrayPixel(0.5));
		assertEquals(-10.0*Math.log10(0.25), p1.calculatePSNR(p2), 0.001);
	}
	
	@Test
	public void unequalPixelRatioTest() {
		Picture p1 = new PictureImpl(2,2);
		Picture p2 = new PictureImpl(2,2);
		
		Pixel a = new ColorPixel(0.4, 0.2, 0.8);
		Pixel eq_a = new ColorPixel(0.37, 0.21, 0.82);
		Pixel neq_a = new ColorPixel(1.0, 0.0, 0.5);
		
		p1.setPixel(0, 0, a);
		p1.setPixel(0, 1, a);
		p1.setPixel(1, 0, a);
		p1.setPixel(1, 1, a);
		
		p2.setPixel(0, 0, eq_a);
		p2.setPixel(0, 1, eq_a);
		p2.setPixel(1, 0, eq_a);
		p2.setPixel(1, 1, eq_a);		
		assertEquals(0.0, p1.unequalPixelRatio(p2), 0.0001);

		p2.setPixel(0, 0, neq_a);
		assertEquals(0.25, p1.unequalPixelRatio(p2), 0.0001);

		p2.setPixel(0, 1, neq_a);
		assertEquals(0.5, p1.unequalPixelRatio(p2), 0.0001);

		p2.setPixel(1, 0, neq_a);
		assertEquals(0.75, p1.unequalPixelRatio(p2), 0.0001);

		p2.setPixel(1, 1, neq_a);
		assertEquals(1.00, p1.unequalPixelRatio(p2), 0.0001);
	}

}
