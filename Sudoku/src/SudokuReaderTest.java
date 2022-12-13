
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuReaderTest {

	SudokuReader sr;

	@BeforeEach
	void setUp() throws Exception {
		sr = new SudokuReader();
	}

	@AfterEach
	void tearDown() throws Exception {
		sr = null;
	}

	@Test
	void testValidCell() {
		assertTrue(sr.validCell(0, 0, 1), "fel i valid cell");
		sr.setCell(0, 0, 1);
		assertFalse(sr.validCell(1, 1, 1), "fel i valid cell");

		sr.setCell(5, 5, 10);
		assertFalse(sr.validCell(5, 5, 10), "fel i valid cell, mer än 9");

		sr.clear();
		sr.setCell(0, 0, -1);
		assertFalse(sr.validCell(0, 0, -1), "negativ inte giltig");
	}

	@Test
	void testEmpty() {
		sr.setCell(0, 0, 1);
		sr.setCell(1, 1, 5);
		sr.clear();
		assertEquals(sr.getCell(0, 0), 0);
		assertEquals(sr.getCell(0, 0), 0);
	}

	@Test
	void testClear() {
		sr.setCell(0, 0, 1);
		sr.setCell(0, 1, 9);
		sr.setCell(0, 2, 8);
		sr.setCell(0, 3, 7);
		sr.setCell(0, 4, 6);
		sr.clear();
		assertEquals(sr.getCell(0, 1), 0);
	}

	@Test
	void testLegal() {
		sr.setCell(0, 1, 7);
		assertTrue(sr.legal(0, 1), "fel i legal");
	}

	@Test
	void testDoubleNumbersR() {
		sr.setCell(0, 0, 1);
		assertFalse(sr.validCell(0, 1, 1), "double numbers i row");

	}

	@Test
	void testDoubleNumbersC() {
		sr.setCell(1, 0, 1);
		assertFalse(sr.validCell(1, 0, 1), "double numbers i col");
	}

	@Test
	public void testSudoku() {
		sr.setCell(0, 2, 8);
		sr.setCell(0, 5, 9);
		sr.setCell(0, 7, 6);
		sr.setCell(0, 8, 2);
		sr.setCell(1, 8, 5);
		sr.setCell(2, 0, 1);
		sr.setCell(2, 2, 2);
		sr.setCell(2, 3, 5);
		sr.setCell(3, 3, 2);
		sr.setCell(3, 4, 1);
		sr.setCell(3, 7, 9);
		sr.setCell(4, 1, 5);
		sr.setCell(4, 6, 6);
		sr.setCell(5, 0, 6);
		sr.setCell(5, 7, 2);
		sr.setCell(5, 8, 8);
		sr.setCell(6, 0, 4);
		sr.setCell(6, 1, 1);
		sr.setCell(6, 3, 6);
		sr.setCell(6, 5, 8);
		sr.setCell(7, 0, 8);
		sr.setCell(7, 1, 6);
		sr.setCell(7, 4, 3);
		sr.setCell(7, 6, 1);
		sr.setCell(8, 6, 4);
		assertTrue(sr.solve(), "Wrong result from solve");
	}

//	@Test
//	void testNoSolboard() {
//		sr.setCell(0, 0, 5);
//		sr.setCell(0, 5, 5);
//		assertFalse(sr.sr(), "fel");
//		sr.clear();
//
//		sr.setCell(0, 0, 5);
//		sr.setCell(0, 7, 5);
//		assertFalse(sr.sr(), "fel");
//		sr.clear();
//
//		sr.setCell(0, 0, 5);
//		sr.setCell(2, 2, 5);
//		assertFalse(sr.sr(), "fel");
//
//	}

}
