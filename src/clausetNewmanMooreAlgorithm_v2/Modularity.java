package clausetNewmanMooreAlgorithm_v2;

import java.util.stream.*;

public class Modularity {
	public static double nodesAsCommunities(AdjMatrix adj) {
		return IntStream.range(0, adj.size())
				.mapToDouble((i)->-Math.pow(adj.degree(i)/(2*adj.edgesNumeber()), 2))
				.sum();
	}
	
	public static double nodesAsCommunities(double[] a) {
		return IntStream.range(0, a.length)
				.mapToDouble((i)->-Math.pow(a[i], 2))
				.sum();
	}
	public static void main(String[] args) {
		AdjMatrix adjMat = new AdjMatrix(new int[][] {{0,1,1,0,0,0,0},
		                                             {1,0,1,1,0,0,0},
		                                             {1,1,0,1,0,0,0},
		                                             {0,1,1,0,1,0,0},
		                                             {0,0,0,1,0,1,1},
		                                             {0,0,0,0,1,0,1},
		                                             {0,0,0,0,1,1,0}});
		System.out.println(String.format("1 node = 1 community modularity: %.10f", Modularity.nodesAsCommunities(adjMat)));
	}
}
