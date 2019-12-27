package clausetNewmanMooreAlgorithm_v2.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clausetNewmanMooreAlgorithm_v2.AdjMatrix;
import clausetNewmanMooreAlgorithm_v2.ClausetNewmanMoore;
import clausetNewmanMooreAlgorithm_v2.test.ModularityControl;

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

}
