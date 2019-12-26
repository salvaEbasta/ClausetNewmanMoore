package clausetNewmanMooreAlgorithm_v1;

import java.util.Arrays;
import java.util.stream.*;

public class AdjMatrix {
	private int[][] mat;
	private int[] nodesDegree;
	private int edgesNum;
	
	public AdjMatrix(int[][] mat) {
		this.mat = mat;
		this.nodesDegree = new int[mat.length];
		IntStream.range(0, mat.length)
					.forEach((i)->nodesDegree[i]=IntStream.range(0, mat.length)
															.map((j)->mat[i][j]).sum()
								);
		this.edgesNum = IntStream.range(0, mat.length)
									.map((i)->IntStream.range(0, mat.length)
														.map((j)->mat[i][j])
														.sum())
									.sum() / 2;
	}
	public int length() {
		return mat.length;
	}
	public int getIndex(int i, int j) {
		return mat[i][j];
	}
	public int getDegree(int i) {
		return nodesDegree[i];
	}
	public int getEdgeNumber() {
		return edgesNum;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer("[");
		Stream.of(mat)
				.map((ai)->Arrays.toString(ai))
				.forEach((as)->sb.append(as));
		return sb.append("]").toString();
	}
	
	public static void main(String[] argss) {
		AdjMatrix adjMat = new AdjMatrix(new int[][]{{0,1,0},
													 {1,0,1},
													 {0,1,0}});
		System.out.println(adjMat.toString());
		System.out.println(String.format("%d", adjMat.getEdgeNumber()));
	}
}
