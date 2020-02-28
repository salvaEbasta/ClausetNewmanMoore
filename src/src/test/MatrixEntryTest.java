package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

import greedyModularity.MatrixEntry;

class MatrixEntryTest {

	@Test
	void equality_test() {
		double value = 0.0;
		int row = 2;
		int col = 3;
		
		MatrixEntry t1 = new MatrixEntry(value, row, col);
		MatrixEntry t2 = new MatrixEntry(value, row, col);
		assertTrue(t1.equals(t2));
		assertFalse(t1.equals(null));
		assertFalse(t1.equals(new Object()));
		
		PriorityQueue<MatrixEntry> H = new PriorityQueue<MatrixEntry>();
		H.add(t1);
		H.remove(t2);
		assertTrue(H.size()==0);
		
		H = new PriorityQueue<MatrixEntry>();
		H.add(t1);
		H.removeIf((t)->t.equals(t2));
		assertTrue(H.size()==0);
	}
	
	@Test
	void compareTo_test() {
		double value = 0.0;
		double value1 = 1.0;
		int row = 2;
		int col = 3;
		
		MatrixEntry t1 = new MatrixEntry(value, row, col);
		MatrixEntry t2 = new MatrixEntry(value1, row, col);
		assertTrue(t1.compareTo(t2)==-1);
		assertTrue(t2.compareTo(t1)==1);
		assertTrue(t1.compareTo(t1)==0);
	}
	
	@Test
	void hashCode_equal_test() {
		double value = 0.0;
		int row = 2;
		int col = 3;
		
		MatrixEntry t1 = new MatrixEntry(value, row, col);
		MatrixEntry t2 = new MatrixEntry(value, row, col);
		assertTrue(t1.equals(t2));
		assertTrue(t1.hashCode()==t2.hashCode());
	}
	
	@Test
	void hashCode_different_test() {
		int row = 2;
		int col = 3;
		
		MatrixEntry t1 = new MatrixEntry(0.0, row, col);
		MatrixEntry t2 = new MatrixEntry(1.0, row, col);
		assertTrue(t1.hashCode()!=t2.hashCode());
	}
}
