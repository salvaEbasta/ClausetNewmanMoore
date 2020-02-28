package greedyModularity_badImplementation.test;

import java.util.stream.IntStream;

import greedyModularity_badImplementation.AdjMatrix;

public class ModularityControl {
	private AdjMatrix adj;
	private int[] nodesToCommunities;

	public ModularityControl(AdjMatrix adj, int[] nodeToCommunity) {
		this.adj = adj;
		this.nodesToCommunities = nodeToCommunity;
	}

	private double e(int ci, int cj) {
		double eij = IntStream.range(0, adj.size())
				.mapToDouble((i)->IntStream.range(0, adj.size())
						.mapToDouble((j)->adj.get(i, j)*delta(nodesToCommunities[i], ci)*delta(nodesToCommunities[j], cj))
						.sum())
				.sum();
		return 1.0/(2*adj.edgesNumber())*eij;
	}
	private double a(int ci) {
		double ai = IntStream.range(0, adj.size())
				.mapToDouble((v)->adj.degree(v)*delta(nodesToCommunities[v], ci))
				.sum();
		return 1.0/(2*adj.edgesNumber())*ai;
	}
	private int delta(int ci, int cj) {
		return ci==cj?1:0;
	}
	public double compute() {
//		System.out.println(String.format("e total = %f", IntStream.of(nodesToCommunities)
//				.distinct()
//				.mapToDouble((i)->e(i,i))
//				.sum()));
		return IntStream.of(nodesToCommunities)
				.distinct()
				.mapToDouble((i)->e(i,i)-Math.pow(a(i), 2))
				.sum();
	}
}
