package clausetNewmanMooreAlgorithm_v2.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import clausetNewmanMooreAlgorithm_v2.AdjMatrix;

class AdjMatrixTest {
	
	@Test
	void adj7x7Structure() {
		AdjMatrix adjMat1 = new AdjMatrix(new int[][] {{0,1,1,0,0,0,0},
											            {1,0,1,1,0,0,0},
											            {1,1,0,1,0,0,0},
											            {0,1,1,0,1,0,0},
											            {0,0,0,1,0,1,1},
											            {0,0,0,0,1,0,1},
											            {0,0,0,0,1,1,0}});
		String expected = "[[0,0; 1,0; 1,0; 0,0; 0,0; 0,0; 0,0], "
				+ "[1,0; 0,0; 1,0; 1,0; 0,0; 0,0; 0,0], "
				+ "[1,0; 1,0; 0,0; 1,0; 0,0; 0,0; 0,0], "
				+ "[0,0; 1,0; 1,0; 0,0; 1,0; 0,0; 0,0], "
				+ "[0,0; 0,0; 0,0; 1,0; 0,0; 1,0; 1,0], "
				+ "[0,0; 0,0; 0,0; 0,0; 1,0; 0,0; 1,0], "
				+ "[0,0; 0,0; 0,0; 0,0; 1,0; 1,0; 0,0]]";
		int edges = 9;
		ArrayList<Double> degrees = new ArrayList<Double>();
		degrees.add(2.0);
		degrees.add(3.0);
		degrees.add(3.0);
		degrees.add(3.0);
		degrees.add(3.0);
		degrees.add(2.0);
		degrees.add(2.0);
		assertEquals(adjMat1.toString(), expected);
		assertEquals(edges, adjMat1.edgesNumber());
		assertTrue(IntStream.range(0, adjMat1.size())
					.allMatch((i)->{
							if(adjMat1.degree(i) == degrees.get(i).doubleValue())
								return true;
							return false;
						})
		);
	}
	
	@Test
	void adj3x3Structure() {
		AdjMatrix adjMat = new AdjMatrix(new int[][]{{0,1,0},
													 {1,0,1},
													 {0,1,0}});
		String expected = "[[0,0; 1,0; 0,0], [1,0; 0,0; 1,0], [0,0; 1,0; 0,0]]";
		int edges = 2;
		double degree0 = 1; double degree1 = 2; double degree2 = 1;
		assertEquals(adjMat.toString(), expected);
		assertEquals(edges, adjMat.edgesNumber());
		assertEquals(degree0, adjMat.degree(0));
		assertEquals(degree1, adjMat.degree(1));
		assertEquals(degree2, adjMat.degree(2));
	}
	
	@Test
	void adj3x3SingleMerges() {
		AdjMatrix adjMat = new AdjMatrix(new int[][]{{0,1,0},
													 {1,0,1},
													 {0,1,0}});
		String expected = "[[0,0; 1,0], [1,0; 0,0]]";
		int mergedEdges = 1;
		double mergedDegree0 = 1; 
		double mergedDegree1 = 1; 
		assertThrows(IndexOutOfBoundsException.class, ()->adjMat.merge(3, 3));
		assertThrows(IndexOutOfBoundsException.class, ()->adjMat.merge(0, 3));
		
		adjMat.merge(0, 1);
		assertEquals(expected, adjMat.toString());
		assertEquals(mergedEdges, adjMat.edgesNumber());
		assertEquals(mergedDegree0, adjMat.degree(0));
		assertEquals(mergedDegree1, adjMat.degree(1));
		assertThrows(IndexOutOfBoundsException.class, ()->adjMat.degree(2));
	}
	
	@Test
	void adj3x3ConsecutiveMerges() {
		AdjMatrix adjMat = new AdjMatrix(new int[][]{{0,1,0},
													 {1,0,1},
													 {0,1,0}});
		String expected = "[0,0]";
		int mergedEdges = 0;
		double mergedDegree0 = 0; 
		adjMat.merge(0, 1);
		assertThrows(IndexOutOfBoundsException.class, ()->adjMat.merge(2, 2));
		assertThrows(IndexOutOfBoundsException.class, ()->adjMat.merge(0, 2));
		
		adjMat.merge(0, 1);
		assertThrows(IndexOutOfBoundsException.class, ()->adjMat.merge(1, 1));
		assertThrows(IndexOutOfBoundsException.class, ()->adjMat.merge(0, 1));
		assertEquals(expected, adjMat.toString());
		assertEquals(mergedEdges, adjMat.edgesNumber());
		assertEquals(mergedDegree0, adjMat.degree(0));
		assertThrows(IndexOutOfBoundsException.class, ()->adjMat.degree(1));
	}
}
