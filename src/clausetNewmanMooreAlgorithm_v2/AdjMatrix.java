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
		initEdgesNumber();
	}
	public int size() {return sparse.size();}
	public double get(int i, int j) {return sparse.get(i, j);}
	public double degree(int i) {return nodesDegree[i];}
	public int edgesNumeber() {return edgesNum;}
	
	/**
	 * Always the same even after a merge
	 */
	private void initEdgesNumber() {
		this.edgesNum = IntStream.range(0, sparse.size())
									.map((i)->IntStream.range(0, sparse.size())
														.map((j)->new Double(sparse.get(i, j)).intValue())
														.sum())
									.sum() / 2;
	}
	
	private void updateNodesDegree() {
		this.nodesDegree = new int[sparse.size()];
		IntStream.range(0, sparse.size())
					.forEach((i)->nodesDegree[i]=IntStream.range(0, sparse.size())
															.map((j)->new Double(sparse.get(i, j)).intValue()).sum()
								);
	}
	
	public void merge(int j, int i) {
		IntStream.range(0, sparse.size())
					.forEach((k)->{
						double value = sparse.get(j, k) + sparse.get(i, k);
						sparse.set(j, k, value);
						sparse.set(k, j, value);
						sparse.set(k, k, 0);
					});
		sparse.removeRowCol(i);
		updateNodesDegree();
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("[");
		IntStream.range(0, sparse.size())
					.forEach((i)->{
						sb.append("[");
						IntStream.range(0, sparse.size()).forEach((j)->sb.append(String.format(" %.1f", sparse.get(i, j))));
						sb.append("]\n");
					});
		return sb.append("]").toString();
	}
	
	public static void main(String[] args) {
		AdjMatrix adjMat = new AdjMatrix(new int[][]{{0,1,0},
													 {1,0,1},
													 {0,1,0}});
		System.out.println(adjMat.toString());
		System.out.println(String.format("%d", adjMat.edgesNumeber()));
		
		adjMat.merge(0, 1);
		System.out.println("Merging 1->0 : Expected: [[1 1][1 0]]");
		System.out.println(adjMat.toString());
	}
}
