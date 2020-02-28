package greedyModularity_badImplementation.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import greedyModularity_badImplementation.AdjMatrix;
import greedyModularity_badImplementation.Modularity;

class ModularityTest {

	@Test
	void adj7x7EachNodeACommunity() {
		AdjMatrix adjMat = new AdjMatrix(new int[][] {{0,1,1,0,0,0,0},
											            {1,0,1,1,0,0,0},
											            {1,1,0,1,0,0,0},
											            {0,1,1,0,1,0,0},
											            {0,0,0,1,0,1,1},
											            {0,0,0,0,1,0,1},
											            {0,0,0,0,1,1,0}});
		assertEquals(Modularity.nodesAsCommunities(adjMat), new ModularityControl(adjMat, IntStream.range(0, adjMat.size()).toArray()).compute());
	}
	
	@Test
	void adj7x7EachNodeACommunityWithA() {
		AdjMatrix adjMat = new AdjMatrix(new int[][] {{0,1,1,0,0,0,0},
											            {1,0,1,1,0,0,0},
											            {1,1,0,1,0,0,0},
											            {0,1,1,0,1,0,0},
											            {0,0,0,1,0,1,1},
											            {0,0,0,0,1,0,1},
											            {0,0,0,0,1,1,0}});
		assertEquals(Modularity.nodesAsCommunities(IntStream.range(0, adjMat.size())
															.mapToDouble((i)->adjMat.degree(i)/(2*adjMat.edgesNumber()))
															.toArray()),
						new ModularityControl(adjMat, IntStream.range(0, adjMat.size()).toArray()).compute());
	}
	
	@Test
	void adj2x2EachNodeACommunity() {
		AdjMatrix adj = new AdjMatrix(new int[][] {{0, 1, 0},
													{1, 0, 0},
													{0, 0, 0}});
		assertEquals(Modularity.nodesAsCommunities(IntStream.range(0, adj.size())
																.mapToDouble((i)->adj.degree(i)/(2*adj.edgesNumber()))
																.toArray()),
						new ModularityControl(adj, IntStream.range(0, adj.size()).toArray()).compute());
		
	}
	
	@Test
	void adj2x2WithCommunityStructure() {
		AdjMatrix adjMat = new AdjMatrix(new int[][] {{0, 1, 0},
														{1, 0, 0},
														{0, 0, 0}});
		double Q = new ModularityControl(adjMat, new int[] {1, 1, 2}).compute();
		assertEquals(Q, 0.0);
	}
}
