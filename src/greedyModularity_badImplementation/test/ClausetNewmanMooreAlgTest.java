package greedyModularity_badImplementation.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import greedyModularity_badImplementation.AdjMatrix;
import greedyModularity_badImplementation.ClausetNewmanMoore;
import greedyModularity_badImplementation.test.ModularityControl;

class ClausetNewmanMooreAlgTest {

	@Test
	void adj3x3Trivial() {
		AdjMatrix adjMat = new AdjMatrix(new int[][] {{0, 1, 0},
														{1, 0, 0},
														{0, 0, 0}});
		String expecComp = "[[var_0, var_1], [var_2]]";
		double Q = new ModularityControl(adjMat, new int[] {1, 1, 2}).compute();
		
		ClausetNewmanMoore cnm = new ClausetNewmanMoore(adjMat);
		cnm.extractCommunities();
		assertEquals(expecComp, cnm.communityComposition().toString());
		assertEquals(Q, cnm.modularity());
	}
	
	@Test
	void adj7x7() {
		AdjMatrix adjMat = new AdjMatrix(new int[][] {{0,1,1,0,0,0,0},
											            {1,0,1,1,0,0,0},
											            {1,1,0,1,0,0,0},
											            {0,1,1,0,1,0,0},
											            {0,0,0,1,0,1,1},
											            {0,0,0,0,1,0,1},
											            {0,0,0,0,1,1,0}});
		String expecComp = "[[var_0, var_1, var_2, var_3], [var_4, var_5, var_6]]";
		double Q = new ModularityControl(adjMat, new int[] {1, 1, 1, 1, 2, 2, 2}).compute();
		
		ClausetNewmanMoore cnm = new ClausetNewmanMoore(adjMat);
		cnm.extractCommunities();
		assertEquals(expecComp, cnm.communityComposition().toString());
		assertEquals(Q, cnm.modularity());
	}

	@Test
	void adj20x20() {
		AdjMatrix adjMat = new AdjMatrix(new int[][] {{0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
											            {1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
											            {0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
											            {0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
											            {0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
											            {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
											            {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
											            {0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
											            {0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
											            {0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
											            {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0},
											            {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1,0,0,0},
											            {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
											            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1},
											            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1},
											            {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,0,0,0},
											            {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0},
											            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
											            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1},
											            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0}});
		String expecComp = "[[var_0, var_1, var_5], [var_2, var_7, var_8], [var_3, var_4, var_9], [var_6, var_12], [var_10, var_11, var_15, var_16], [var_13, var_14, var_18, var_19], [var_17]]";
		double Q = new ModularityControl(adjMat, new int[] {1, 1, 2, 3, 3, 1, 4, 2 , 2, 3, 5, 5, 4, 7, 7, 5, 5, 6, 7, 7}).compute();
		
		ClausetNewmanMoore cnm = new ClausetNewmanMoore(adjMat);
		cnm.extractCommunities();
		assertEquals(expecComp, cnm.communityComposition().toString());
		assertEquals(Q, cnm.modularity());
	}
}
