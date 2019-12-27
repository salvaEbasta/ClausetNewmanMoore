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
	public double degree(int i) throws IndexOutOfBoundsException{
		if(i>=sparse.size())
			throw new IndexOutOfBoundsException();
		return nodesDegree[i];
	}
	public int edgesNumber() {return edgesNum;}
	
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
	
	public void merge(int j, int i) throws IndexOutOfBoundsException{
		if(j>=sparse.size() || i>=sparse.size())
			throw new IndexOutOfBoundsException();
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
		if(sparse.size() == 1)
			sb.append(String.format("%.1f", sparse.get(0, 0)));
		else {
			sb.append("[");
			sb.append(String.format("%.1f", sparse.get(0, 0)));
			IntStream.range(1, sparse.size())
						.forEach((j)->sb.append(String.format("; %.1f", sparse.get(0, j))));
			sb.append("]");
			IntStream.range(1, sparse.size())
						.forEach((i)->{
								sb.append(", [");
								sb.append(String.format("%.1f", sparse.get(i, 0)));
								IntStream.range(1, sparse.size())
											.forEach((j)->sb.append(String.format("; %.1f", sparse.get(i, j))));
								sb.append("]");
							});
		}
		return sb.append("]").toString();
	}
}
