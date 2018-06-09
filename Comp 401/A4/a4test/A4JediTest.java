package a4test;

import a4jedi.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class A4JediTest {

	@Test
	public void testThreshold() {
		PixelTransformation xform = new Threshold(0.25);
		Pixel p1 = new GrayPixel(0.24);
		Pixel p2 = new GrayPixel(0.26);
		Pixel black = new GrayPixel(0.0);
		Pixel white = new GrayPixel(1.0);
		
		Pixel res = xform.transform(p1);
		assertTrue("Expected threshold transform to produce black.",
				comparePixels(res, black));
		res = xform.transform(p2);
		assertTrue("Expected threshold transform to produce white.",
				comparePixels(res, white));
	}

	@Test
	public void testGamma() {
		PixelTransformation xform = new GammaCorrect(0.4);
		Pixel p1 = new ColorPixel(0.2, 0.4, 0.7);
		Pixel p2 = new ColorPixel(Math.pow(0.2, (1.0/0.4)),
								  Math.pow(0.4, (1.0/0.4)),
								  Math.pow(0.7, (1.0/0.4)));
		
		Pixel res = xform.transform(p1);
		assertTrue("Gamma correct transform doesn't seem to produce correct pixel",
				comparePixels(res, p2));
	}
	
	@Test
	public void testTransformedPicture() {
		Picture p = new PictureImpl(2,2);
		Pixel a = new ColorPixel(0.2, 0.3, 0.4);
		Pixel b = new ColorPixel(0.7, 0.7, 0.2);
		Pixel c = new GrayPixel(0.3);
		Pixel d = new GrayPixel(0.7);

		p.setPixel(0, 0, a);
		p.setPixel(0, 1, b);
		p.setPixel(1, 0, c);
		p.setPixel(1, 1, d);
		
		PixelTransformation thresh = new Threshold(0.45);
		PixelTransformation gamma = new GammaCorrect(1.3);
		
		Pixel white = new GrayPixel(1.0);
		Pixel black = new GrayPixel(0.0);
		
		Pixel ga = new ColorPixel(Math.pow(0.2, (1.0/1.3)), 
								  Math.pow(0.3, (1.0/1.3)), 
								  Math.pow(0.4, (1.0/1.3)));
		Pixel gb = new ColorPixel(Math.pow(0.7, (1.0/1.3)), 
				  				  Math.pow(0.7, (1.0/1.3)), 
				  				  Math.pow(0.2, (1.0/1.3)));
		Pixel gc = new ColorPixel(Math.pow(0.3, (1.0/1.3)), 
				                  Math.pow(0.3, (1.0/1.3)), 
				                  Math.pow(0.3, (1.0/1.3)));
		Pixel gd = new ColorPixel(Math.pow(0.7, (1.0/1.3)), 
				                  Math.pow(0.7, (1.0/1.3)), 
				                  Math.pow(0.7, (1.0/1.3)));
		
		Picture gamma_transformed = p.transform(gamma);
		Picture thresh_transformed = p.transform(thresh);
		
		assertTrue("Pixel value from gamma transformed picture incorrect",
				comparePixels(ga, gamma_transformed.getPixel(0, 0)));
		assertTrue("Pixel value from gamma transformed picture incorrect",
				comparePixels(gb, gamma_transformed.getPixel(0, 1)));
		assertTrue("Pixel value from gamma transformed picture incorrect",
				comparePixels(gc, gamma_transformed.getPixel(1, 0)));
		assertTrue("Pixel value from gamma transformed picture incorrect",
				comparePixels(gd, gamma_transformed.getPixel(1, 1)));

		assertTrue("Pixel value from threshold transformed picture incorrect",
				comparePixels(black, thresh_transformed.getPixel(0, 0)));
		assertTrue("Pixel value from threshold transformed picture incorrect",
				comparePixels(white, thresh_transformed.getPixel(0, 1)));
		assertTrue("Pixel value from threshold transformed picture incorrect",
				comparePixels(black, thresh_transformed.getPixel(1, 0)));
		assertTrue("Pixel value from threshold transformed picture incorrect",
				comparePixels(white, thresh_transformed.getPixel(1, 1)));

		p.setPixel(0, 0, c);
		p.setPixel(0, 1, a);
		p.setPixel(1, 0, d);
		p.setPixel(1, 1, b);
		
		assertTrue("Pixel value from gamma transformed picture incorrect",
				comparePixels(gc, gamma_transformed.getPixel(0, 0)));
		assertTrue("Pixel value from gamma transformed picture incorrect",
				comparePixels(ga, gamma_transformed.getPixel(0, 1)));
		assertTrue("Pixel value from gamma transformed picture incorrect",
				comparePixels(gd, gamma_transformed.getPixel(1, 0)));
		assertTrue("Pixel value from gamma transformed picture incorrect",
				comparePixels(gb, gamma_transformed.getPixel(1, 1)));

		assertTrue("Pixel value from threshold transformed picture incorrect",
				comparePixels(black, thresh_transformed.getPixel(0, 0)));
		assertTrue("Pixel value from threshold transformed picture incorrect",
				comparePixels(black, thresh_transformed.getPixel(0, 1)));
		assertTrue("Pixel value from threshold transformed picture incorrect",
				comparePixels(white, thresh_transformed.getPixel(1, 0)));
		assertTrue("Pixel value from threshold transformed picture incorrect",
				comparePixels(white, thresh_transformed.getPixel(1, 1)));
	}
	
	@Test
	public void testTransformedSetPixelException() {
		Picture p = new PictureImpl(2,2);
		PixelTransformation xform = new Threshold(0.5);
		Picture xform_pic = p.transform(xform);
		try {
			xform_pic.setPixel(0, 0, new GrayPixel(0));
			fail("setPixel on transformed pixel should throw UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		} catch (Exception e) {
			fail("Expected UnsupportedOperationException, detected some other kind of exception.");
		}
	}
	
	private static boolean comparePixels(Pixel a, Pixel b) {
		return ((Math.abs(a.getRed()-b.getRed()) < 0.001) &&
				(Math.abs(a.getGreen()-b.getGreen()) < 0.001) &&
				(Math.abs(a.getBlue()-b.getBlue()) < 0.001));
	}

}
