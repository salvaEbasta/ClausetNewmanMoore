package clausetNewmanMooreAlgorithm_v1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class Modularity {
	private AdjMatrix adj;
	private int[] nodesToCommunities;
	
	public Modularity(AdjMatrix adj, int[] nodeToCommunity) {
		this.adj = adj;
		this.nodesToCommunities = nodeToCommunity;
	}
	public int[] nodesToCommunities() {
		return nodesToCommunities;
	}
	
	private double e(int ci, int cj) {
		double eij = IntStream.range(0, adj.length())
						.mapToDouble((i)->IntStream.range(0, adj.length())
								.mapToDouble((j)->adj.getIndex(i, j)*delta(nodesToCommunities[i], ci)*delta(nodesToCommunities[j], cj))
								.sum())
						.sum();
		return 1.0/(2*adj.getEdgeNumber())*eij;
	}
	private double a(int ci) {
		double ai = IntStream.range(0, adj.length())
				.mapToDouble((v)->adj.getDegree(v)*delta(nodesToCommunities[v], ci))
				.sum();
		return 1.0/(2*adj.getEdgeNumber())*ai;
	}
	private int delta(int ci, int cj) {
		return ci==cj?1:0;
	}
	public double compute(List<Double> a) {
		return IntStream.of(nodesToCommunities)
				.distinct()
				.mapToDouble((i)->e(i,i)-Math.pow(a.get(i), 2))
				.sum();
	}
	public double compute() {
		return IntStream.of(nodesToCommunities)
						.distinct()
						.mapToDouble((i)->e(i,i)-Math.pow(a(i), 2))
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
		//Modularity m = new Modularity(adjMat, new int[]{1,1,1,1,2,2,2});
		Modularity m = new Modularity(adjMat, IntStream.range(0, adjMat.length()).toArray());
		System.out.println(Arrays.toString(m.nodesToCommunities()));
		System.out.println(String.format("modularity: %.10f", m.compute()));
		m = new Modularity(adjMat, new int[]{0,1,1,3,4,5,6});
		System.out.println(Arrays.toString(m.nodesToCommunities()));
		System.out.println(String.format("modularity: %.10f", m.compute()));
		m = new Modularity(adjMat, new int[]{1,1,1,3,4,5,6});
		System.out.println(Arrays.toString(m.nodesToCommunities()));
		System.out.println(String.format("modularity: %.10f", m.compute()));
		m = new Modularity(adjMat, new int[]{1,1,1,1,4,5,6});
		System.out.println(Arrays.toString(m.nodesToCommunities()));
		System.out.println(String.format("modularity: %.10f", m.compute()));
		m = new Modularity(adjMat, new int[]{1,1,1,1,4,4,6});
		System.out.println(Arrays.toString(m.nodesToCommunities()));
		System.out.println(String.format("modularity: %.10f", m.compute()));
		m = new Modularity(adjMat, new int[]{1,1,1,1,4,4,4});
		System.out.println(Arrays.toString(m.nodesToCommunities()));
		System.out.println(String.format("modularity: %.10f", m.compute()));
	}
}
