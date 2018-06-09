package a3test;

import static org.junit.Assert.*;

import org.junit.Test;
import a3novice.Pixel;
import a3novice.ColorPixel;
import a3novice.GrayPixel;
import org.junit.Assert;

public class A3NoviceTest {

	@Test
	public void colorPixelConstructorTest() {
		Pixel p = new ColorPixel(0.2, 0.3, 0.4);
		Assert.assertEquals(0.2, p.getRed(), 0.01);
		Assert.assertEquals(0.3,  p.getGreen(), 0.01);
		Assert.assertEquals(0.4, p.getBlue(), 0.01);
	}
	
	@Test
	public void colorPixelGetCharTest() {
		
		Pixel p = new ColorPixel(0.05, 0.05, 0.05);
		Assert.assertEquals('#', p.getChar());		
		p = new ColorPixel(0.15, 0.15, 0.15);
		Assert.assertEquals('M', p.getChar());
		p = new ColorPixel(0.25, 0.25, 0.25);
		Assert.assertEquals('X', p.getChar());
		p = new ColorPixel(0.35, 0.35, 0.35);
		Assert.assertEquals('D', p.getChar());
		p = new ColorPixel(0.45, 0.45, 0.45);
		Assert.assertEquals('<', p.getChar());
		p = new ColorPixel(0.55, 0.55, 0.55);
		Assert.assertEquals('>', p.getChar());
		p = new ColorPixel(0.65, 0.65, 0.65);
		Assert.assertEquals('s', p.getChar());
		p = new ColorPixel(0.75, 0.75, 0.75);
		Assert.assertEquals(':', p.getChar());
		p = new ColorPixel(0.85, 0.85, 0.85);
		Assert.assertEquals('-', p.getChar());
		p = new ColorPixel(0.95, 0.95, 0.95);
		Assert.assertEquals(' ', p.getChar());

	}
	
	@Test
	public void colorPixelIntensityTest() {
		Pixel p = new ColorPixel(0.34, 0.34, 0.34);
		Assert.assertEquals(0.34, p.getIntensity(), 0.01);
		p = new ColorPixel(0.1, 0.5, 0.8);
		Assert.assertEquals(0.299*0.1+0.587*0.5+0.8*0.114, p.getIntensity(), 0.01);
		p = new ColorPixel(1.0, 0, 0.0);
		Assert.assertEquals(0.299, p.getIntensity(), 0.001);
		p = new ColorPixel(0.0, 1.0, 0.0);
		Assert.assertEquals(0.587, p.getIntensity(), 0.001);
		p = new ColorPixel(0.0, 0.0, 1.0);
		Assert.assertEquals(0.114, p.getIntensity(), 0.001);
	}

	@Test
	public void grayPixelConstructorTest() {
		Pixel p = new GrayPixel(0.2);
		Assert.assertEquals(0.2, p.getRed(), 0.01);
		Assert.assertEquals(0.2,  p.getGreen(), 0.01);
		Assert.assertEquals(0.2, p.getBlue(), 0.01);
	}
	
	@Test
	public void grayPixelGetCharTest() {
		
		Pixel p = new GrayPixel(0.05);
		Assert.assertEquals('#', p.getChar());		
		p = new GrayPixel(0.15);
		Assert.assertEquals('M', p.getChar());
		p = new GrayPixel(0.25);
		Assert.assertEquals('X', p.getChar());
		p = new GrayPixel(0.35);
		Assert.assertEquals('D', p.getChar());
		p = new GrayPixel(0.45);
		Assert.assertEquals('<', p.getChar());
		p = new GrayPixel(0.55);
		Assert.assertEquals('>', p.getChar());
		p = new GrayPixel(0.65);
		Assert.assertEquals('s', p.getChar());
		p = new GrayPixel(0.75);
		Assert.assertEquals(':', p.getChar());
		p = new GrayPixel(0.85);
		Assert.assertEquals('-', p.getChar());
		p = new GrayPixel(0.95);
		Assert.assertEquals(' ', p.getChar());

	}
	
	@Test
	public void grayPixelRGBTest() {
		Pixel p = new GrayPixel(0.34);
		Assert.assertEquals(0.34, p.getIntensity(), 0.001);
		Assert.assertEquals(0.34, p.getRed(), 0.001);
		Assert.assertEquals(0.34, p.getGreen(), 0.001);
		Assert.assertEquals(0.34, p.getBlue(), 0.001);
	}
	
	@Test
	public void constructorErrorTest() {
		try {
			Pixel p = new GrayPixel(-1.0);
			fail("Constructing new GrayPixel with intensity value < 0 did not throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Exception e) {
			fail("Constructing new GrayPixel with intensity value < 0 did not throw RuntimeException");
		}
		try {
			Pixel p = new GrayPixel(2.0);
			fail("Constructing new GrayPixel with intensity value > 1.0 did not throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Exception e) {
			fail("Constructing new GrayPixel with intensity value > 1.0 did not throw RuntimeException");
		}
		try {
			Pixel p = new ColorPixel(-1.0, 0.5, 0.5);
			fail("Constructing new ColorPixel with component value < 0 did not throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Exception e) {
			fail("Constructing new ColorPixel with component value < 0 did not throw RuntimeException");
		}
		try {
			Pixel p = new ColorPixel(2.0, 0.5, 0.5);
			fail("Constructing new ColorPixel with component value > 1.0 did not throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Exception e) {
			fail("Constructing new ColorPixel with component value > 1.0 did not throw RuntimeException");
		}

	}

}
