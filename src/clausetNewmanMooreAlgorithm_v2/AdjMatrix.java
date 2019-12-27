package clausetNewmanMooreAlgorithm_v2;

import java.util.stream.*;

public class AdjMatrix {
	private SparseMatrix sparse;
	private int[] nodesDegree;
	private int edgesNum;
	
	public AdjMatrix(int[][] mat) {
		this.sparse = new SparseMatrix(mat.length);
		IntStream.range(0, mat.length)
					.forEach((i)->IntStream.range(0, mat.length)
											.forEach((j)->sparse.set(i, j, mat[i][j])));
		updateNodesDegree();
		updateEdgesNumber();
	}
	public int size() {return sparse.size();}
	public double get(int i, int j) {return sparse.get(i, j);}
	public double degree(int i) {return nodesDegree[i];}
	public int edgesNumeber() {return edgesNum;}
	
	/**
	 * Always the same even after a merge
	 */
	private void updateEdgesNumber() {
		this.edgesNum = IntStream.range(0, sparse.size())
									.map((i)->IntStream.range(0, sparse.size())
														.map((j)->new Double(sparse.get(i, j)).intValue())
														.sum())
									.sum()/2;
	}
	
	private void updateNodesDegree() {
		this.nodesDegree = new int[sparse.size()];
		IntStream.range(0, sparse.size())
					.forEach((i)->nodesDegree[i]=IntStream.range(0, sparse.size())
															.map((j)->new Double(sparse.get(i, j)).intValue())
															.sum()
					);
	}
	
	public void merge(int j, int i) {
		IntStream.range(0, sparse.size())
					.forEach((c)->{
						double value = sparse.get(j, c) + sparse.get(i, c);
						sparse.set(j, c, value);
						sparse.set(c, j, value);
					});
		sparse.set(j, j, 0);
		sparse.removeRowCol(i);
		updateEdgesNumber();
		updateNodesDegree();
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("[");
		IntStream.range(0, sparse.size())
					.forEach((i)->{
						if(sparse.size() == 1) {
							sb.append(String.format(" %.1f", sparse.get(i, i)));
						}else {
							sb.append("[");
							IntStream.range(0, sparse.size()).forEach((j)->sb.append(String.format(" %.1f", sparse.get(i, j))));
							sb.append("]\n");
						}
					});
		return sb.append("]").toString();
	}
	
	public static void main(String[] args) {
		AdjMatrix adjMat = new AdjMatrix(new int[][]{{0,1,0},
													 {1,0,1},
													 {0,1,0}});
		System.out.println(adjMat.toString());
		System.out.println(String.format("tot edges: %d", adjMat.edgesNumeber()));
		System.out.println(String.format("degree 0: %f", adjMat.degree(0)));
		System.out.println(String.format("degree 1: %f", adjMat.degree(1)));
		System.out.println(String.format("degree 2 :%f", adjMat.degree(2)));
		
		adjMat.merge(0, 1);
		System.out.println("Merging 1->0 : Expected: [[0 1][1 0]]");
		System.out.println(adjMat.toString());

		adjMat.merge(0, 1);
		System.out.println("Merging 1->0 : Expected: [0]");
		System.out.println(adjMat.toString());
		
		AdjMatrix adjMat1 = new AdjMatrix(new int[][] {{0,1,1,0,0,0,0},
											            {1,0,1,1,0,0,0},
											            {1,1,0,1,0,0,0},
											            {0,1,1,0,1,0,0},
											            {0,0,0,1,0,1,1},
											            {0,0,0,0,1,0,1},
											            {0,0,0,0,1,1,0}});
		System.out.println(adjMat1.toString());
		System.out.println(String.format("tot edges: %d", adjMat1.edgesNumeber()));
		IntStream.range(0, adjMat1.size()).forEach((i)->System.out.println(String.format("degree %d :%f", i, adjMat1.degree(i))));
	}
}
