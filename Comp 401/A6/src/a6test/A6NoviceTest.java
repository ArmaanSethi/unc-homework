package a6test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a6novice.*;

public class A6NoviceTest {
	
	private static final Pixel RED = new ColorPixel(1,0,0);
	private static final Pixel GREEN = new ColorPixel(0,1,0);
	private static final Pixel BLUE = new ColorPixel(0,0,1);
	private static final Pixel WHITE = new GrayPixel(1);
	private static final Pixel BLACK = new GrayPixel(0);
	
	static public String[] getTestNames() {
		String[] test_names = new String[4];
		
		test_names[0] = "testGetSetPixelByCoord";
		test_names[1] = "testExtractSubpictureByCoord";
		test_names[2] = "testPixelIteratorBasic";
		test_names[3] = "testPixelIteratorSetterError";
		return test_names;
	}
	
	static public String[] getTestDescriptions() {
		String[] test_descriptions = new String[4];
		
		test_descriptions[0] = "Ensure getters and setters using coordinate object references work.";
		test_descriptions[1] = "Ensure proper extraction of subpicture given inclusive coordinates on any pair of diagonal corners.";
		test_descriptions[2] = "Ensure proper function of hasNext and next methods for PixelIterator.";
		test_descriptions[3] = "Ensure use of remove method for PixelIterator results in UnsupportedOperationException.";
		return test_descriptions;
	}
	
	@Test
	public void testGetSetPixelByCoord() {
		Picture source_1 = initializeSource1();
		Coordinate source_a = new Coordinate (2, 1);
		Coordinate source_b = new Coordinate (2, 2);
		Coordinate source_c = new Coordinate (3, 1);
		Coordinate source_d = new Coordinate (3, 2);
		
		SubPicture subpic_1 = new SubPictureImpl(source_1, 2, 1, 2, 2);
		Coordinate sub_a = new Coordinate (0, 0);
		Coordinate sub_b = new Coordinate (0, 1);
		Coordinate sub_c = new Coordinate (1, 0);
		Coordinate sub_d = new Coordinate (1, 1);
		
		
		assertEquals("Pixel retrieved by coordinate does not match pixel retrieved by x and y",
				source_1.getPixel(source_a), source_1.getPixel(2, 1));
		assertEquals("Pixel retrieved by coordinate does not match pixel retrieved by x and y",
				source_1.getPixel(source_b), source_1.getPixel(2, 2));
		assertEquals("Pixel retrieved by coordinate does not match pixel retrieved by x and y",
				subpic_1.getPixel(sub_c), subpic_1.getPixel(1, 0));
		assertEquals("Pixel retrieved by coordinate does not match pixel retrieved by x and y",
				subpic_1.getPixel(sub_d), subpic_1.getPixel(1, 1));
		
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				source_1.getPixel(source_a), subpic_1.getPixel(sub_a));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				source_1.getPixel(source_b), subpic_1.getPixel(sub_b));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				source_1.getPixel(source_c), subpic_1.getPixel(sub_c));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				source_1.getPixel(source_d), subpic_1.getPixel(sub_d));
		
		subpic_1.setPixel(sub_a, RED);
		assertEquals("Pixel retrieved using coordinate after setting does not match expected value",
				RED, subpic_1.getPixel(sub_a));
		assertEquals("Pixel retrieved using x and y after setting does not match expected value",
				RED, subpic_1.getPixel(0, 0));
		assertEquals("Pixel in source not changed after setting through subpicture",
				RED, source_1.getPixel(source_a));
	}
	
	@Test
	public void testExtractSubpictureByCoord() {
		Picture source_1 = initializeSource1();
		Coordinate source_a = new Coordinate (2, 1);
		Coordinate source_b = new Coordinate (2, 2);
		Coordinate source_c = new Coordinate (3, 1);
		Coordinate source_d = new Coordinate (3, 2);
		
		SubPicture subpic_1a = source_1.extract(source_a, source_d);
		Coordinate sub1a_a = new Coordinate (0, 0);
		Coordinate sub1a_b = new Coordinate (0, 1);
		Coordinate sub1a_c = new Coordinate (1, 0);
		Coordinate sub1a_d = new Coordinate (1, 1);
		assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source",
				source_1.getPixel(source_a), subpic_1a.getPixel(sub1a_a));
		assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source",
				source_1.getPixel(source_b), subpic_1a.getPixel(sub1a_b));
		assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source",
				source_1.getPixel(source_c), subpic_1a.getPixel(sub1a_c));
		assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source",
				source_1.getPixel(source_d), subpic_1a.getPixel(sub1a_d));
		
		SubPicture subpic_1b = source_1.extract(source_b, source_c);
		Coordinate sub1b_a = new Coordinate (0, 0);
		Coordinate sub1b_b = new Coordinate (0, 1);
		Coordinate sub1b_c = new Coordinate (1, 0);
		Coordinate sub1b_d = new Coordinate (1, 1);
		assertEquals("Pixel retrieved from subpicture using top-right/bottom-left coordinates does not match expected pixel value from source",
				source_1.getPixel(source_a), subpic_1b.getPixel(sub1b_a));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				source_1.getPixel(source_b), subpic_1b.getPixel(sub1b_b));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				source_1.getPixel(source_c), subpic_1b.getPixel(sub1b_c));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				source_1.getPixel(source_d), subpic_1b.getPixel(sub1b_d));
		
		SubPicture subpic_1c = source_1.extract(source_c, source_b);
		Coordinate sub1c_a = new Coordinate (0, 0);
		Coordinate sub1c_b = new Coordinate (0, 1);
		Coordinate sub1c_c = new Coordinate (1, 0);
		Coordinate sub1c_d = new Coordinate (1, 1);
		assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source",
				source_1.getPixel(source_a), subpic_1c.getPixel(sub1c_a));
		assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source",
				source_1.getPixel(source_b), subpic_1c.getPixel(sub1c_b));
		assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source",
				source_1.getPixel(source_c), subpic_1c.getPixel(sub1c_c));
		assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source",
				source_1.getPixel(source_d), subpic_1c.getPixel(sub1c_d));
		
		SubPicture subpic_1d = source_1.extract(source_d, source_a);
		Coordinate sub1d_a = new Coordinate (0, 0);
		Coordinate sub1d_b = new Coordinate (0, 1);
		Coordinate sub1d_c = new Coordinate (1, 0);
		Coordinate sub1d_d = new Coordinate (1, 1);
		assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source",
				source_1.getPixel(source_a), subpic_1d.getPixel(sub1d_a));
		assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source",
				source_1.getPixel(source_b), subpic_1d.getPixel(sub1d_b));
		assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source",
				source_1.getPixel(source_c), subpic_1d.getPixel(sub1d_c));
		assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source",
				source_1.getPixel(source_d), subpic_1d.getPixel(sub1d_d));
		
		SubPicture subpic_2 = subpic_1a.extract(sub1a_d, sub1a_d);
		Coordinate sub2_d = new Coordinate (0, 0);
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				source_1.getPixel(source_d), subpic_2.getPixel(sub2_d));
	}
	
	@Test
	public void testPixelIteratorBasic() {
		Picture source_1 = initializeSource1();
		Iterator<Pixel> s1_iterator = source_1.iterator();
		// (0, 0)
		assertTrue(s1_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				RED, s1_iterator.next());
		// (1, 0)
		assertTrue(s1_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				WHITE, s1_iterator.next());
		// (2, 0)
		assertTrue(s1_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				GREEN, s1_iterator.next());
		// (3, 0)
		assertTrue(s1_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				BLACK, s1_iterator.next());
		// (0, 1)
		assertTrue(s1_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				GREEN, s1_iterator.next());
		// (1, 1)
		assertTrue(s1_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				BLACK, s1_iterator.next());
		// (2, 1)
		assertTrue(s1_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				BLUE, s1_iterator.next());
		// (3, 1)
		assertTrue(s1_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				RED, s1_iterator.next());
		// (0, 2)
		assertTrue(s1_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				BLUE, s1_iterator.next());
		// (1, 2)
		assertTrue(s1_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				RED, s1_iterator.next());
		// (2, 2)
		assertTrue(s1_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				WHITE, s1_iterator.next());
		// (3, 2)
		assertTrue(s1_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				GREEN, s1_iterator.next());
		assertFalse(s1_iterator.hasNext());
		
		Picture source_2 = initializeSource2();
		Iterator<Pixel> s2_iterator = source_2.iterator();
		// (0, 0)
		assertTrue(s2_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				RED, s2_iterator.next());
		// (1, 0)
		assertTrue(s2_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				BLUE, s2_iterator.next());
		// (0, 1)
		assertTrue(s2_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				GREEN, s2_iterator.next());
		// (1, 1)
		assertTrue(s2_iterator.hasNext());
		assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel",
				WHITE, s2_iterator.next());
		assertFalse(s2_iterator.hasNext());
	}
	
	@Test
	public void testPixelIteratorSetterError() {
		Picture source_1 = initializeSource1();
		Iterator<Pixel> s1_iterator = source_1.iterator();
		
		try {
			s1_iterator.remove();
			fail("remove on PixelIterator should throw UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		} catch (Exception e) {
			fail("Expected UnsupportedOperationException but detected some other kind of exception");
		}
	}
	
	private Picture initializeSource1() {
		Picture s1 = new PictureImpl(4, 3);
		
		s1.setPixel(0, 0, RED);
		s1.setPixel(1, 0, WHITE);
		s1.setPixel(2, 0, GREEN);
		s1.setPixel(3, 0, BLACK);
		s1.setPixel(0, 1, GREEN);
		s1.setPixel(1, 1, BLACK);
		s1.setPixel(2, 1, BLUE);
		s1.setPixel(3, 1, RED);
		s1.setPixel(0, 2, BLUE);
		s1.setPixel(1, 2, RED);
		s1.setPixel(2, 2, WHITE);
		s1.setPixel(3, 2, GREEN);
		return s1;
	}
	
	private Picture initializeSource2() {
		Picture s2 = new PictureImpl(2, 2);
		
		s2.setPixel(0, 0, RED);
		s2.setPixel(1, 0, BLUE);
		s2.setPixel(0, 1, GREEN);
		s2.setPixel(1, 1, WHITE);
		return s2;
	}

}
