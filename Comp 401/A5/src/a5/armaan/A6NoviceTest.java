package a5.armaan;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a6novice.*;
import junit.framework.Assert;

public class A6NoviceTest {
		
	static public String[] getTestNames() {
		String[] test_names = new String[5];
		
		test_names[0] = "getPixelTest";
		test_names[1] = "setPixelTest";
		test_names[2] = "extractTest";
		test_names[3] = "hasNextTest";
		test_names[4] = "nextTest";
		
		return test_names;
	}
		
	@Test
	public void getPixelTest() {
		Picture p = new PictureImpl(6,4);
		Coordinate c1 = new Coordinate(3,3);
		Coordinate c2 = new Coordinate(4,4);
		assertEquals(p.getPixel(3, 3), p.getPixel(c1));
		assertEquals(p.getPixel(4, 4), p.getPixel(c2));
	}
	@Test
	public void setPixelTest() {
		Picture p = new PictureImpl(6,4);
		Pixel pix = new ColorPixel(20,20,20);
		Coordinate c1 = new Coordinate(3,3);
		Coordinate c2 = new Coordinate(4,4);
		p.setPixel(c1, pix);
		p.setPixel(c2, pix);
		assertEquals(p.getPixel(c1), pix);
		assertEquals(p.getPixel(c2), pix);
	}
	@Test
	public void extractTest() {
	}
	@Test
	public void hasNextTest() {
	}
	@Test
	public void nextTest() {
	}
}
