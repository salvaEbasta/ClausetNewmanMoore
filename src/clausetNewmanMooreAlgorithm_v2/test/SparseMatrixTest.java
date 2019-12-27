package clausetNewmanMooreAlgorithm_v2.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import clausetNewmanMooreAlgorithm_v2.SparseMatrix;

class SparseMatrixTest {

	@Test
	void matrixOfZeros() {
		SparseMatrix r2x2 = new SparseMatrix(2);
		IntStream.range(0, r2x2.size())
					.forEach((i)->IntStream.range(0, r2x2.size())
											.forEach((j)->assertEquals(r2x2.get(i, j), 0.0)));
		assertEquals(r2x2.storedItems(), 0);
		assertEquals(r2x2.size(), 2);
	}
	
	@Test
	void matrixOneItem() {
		SparseMatrix r2x2 = new SparseMatrix(2);
		r2x2.set(0,0,2);
		assertEquals(r2x2.get(0, 0), 2);
		assertEquals(r2x2.get(0, 1), 0);
		assertEquals(r2x2.get(1, 0), 0);
		assertEquals(r2x2.get(1, 1), 0);
		assertEquals(r2x2.storedItems(), 1);
		assertEquals(r2x2.size(), 2);
	}

	@Test 
	void oneItemToZero(){
		SparseMatrix r2x2 = new SparseMatrix(2);
		r2x2.set(0,0,2);
		assertEquals(r2x2.get(0, 0), 2);
		assertEquals(r2x2.get(0, 1), 0);
		assertEquals(r2x2.get(1, 0), 0);
		assertEquals(r2x2.get(1, 1), 0);
		assertEquals(r2x2.storedItems(), 1);
		assertEquals(r2x2.size(), 2);
		
		r2x2.set(0,0,0);
		assertEquals(r2x2.get(0, 0), 0);
		assertEquals(r2x2.get(0, 1), 0);
		assertEquals(r2x2.get(1, 0), 0);
		assertEquals(r2x2.get(1, 1), 0);
		assertEquals(r2x2.storedItems(), 0);
		assertEquals(r2x2.size(), 2);
	}
	
	@Test
	void reductionOfRowsCols(){
		String expected = "[[(0, 0):1,000000|(0, 1):2,000000|][(1, 0):3,000000|(1, 1):4,000000|]]";
		String expected1 = "[[(0, 0):4,000000|]]";
		String expected2 = "[]";
		SparseMatrix r2x2 = new SparseMatrix(2);
		r2x2.set(0, 0, 1);
		r2x2.set(0, 1, 2);
		r2x2.set(1, 0, 3);
		r2x2.set(1, 1, 4);
		assertEquals(r2x2.toString(), expected);
		assertEquals(r2x2.storedItems(), 4);
		assertEquals(r2x2.size(), 2);
		
		r2x2.removeRowCol(0);
		assertEquals(r2x2.toString(), expected1);
		assertEquals(r2x2.storedItems(), 1);
		assertEquals(r2x2.size(), 1);
		assertEquals(r2x2.get(0,0), 4);
		
		r2x2.removeRowCol(0);
		assertEquals(r2x2.toString(), expected2);
		assertEquals(r2x2.storedItems(), 0);
		assertEquals(r2x2.size(), 0);
		
		assertThrows(IndexOutOfBoundsException.class, ()->r2x2.removeRowCol(0));
	}
	
}
