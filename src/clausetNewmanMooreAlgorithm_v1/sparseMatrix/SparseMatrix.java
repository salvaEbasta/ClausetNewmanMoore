package clausetNewmanMooreAlgorithm_v1.sparseMatrix;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.stream.*;

import clausetNewmanMooreAlgorithm_v1.AdjMatrix;

public class SparseMatrix {
	private ArrayList<TreeMap<Integer,Double>> sparse_rows;
	private ArrayList<MatrixEntry> max_heap;
	
	/**Square sparse matrix 
	 * @param length
	 */
	public SparseMatrix(int length) {
		this.sparse_rows = new ArrayList<TreeMap<Integer,Double>>();
		this.max_heap = new ArrayList<MatrixEntry>();
		IntStream.range(0, length).forEach((i)->{
			sparse_rows.add(new TreeMap<Integer,Double>());
			max_heap.add(MatrixEntry.generateInvalid());
		});
	}
	
	@Deprecated
	public SparseMatrix(int[][] mat) {
		this.sparse_rows = new ArrayList<TreeMap<Integer,Double>>();
		this.max_heap = new ArrayList<MatrixEntry>();
		IntStream.range(0, mat.length).forEach((i)->{
				sparse_rows.add(new TreeMap<Integer,Double>());
				max_heap.add(MatrixEntry.generateInvalid());
				IntStream.range(0, mat.length)
							.forEach((j)->{
								sparse_rows.get(i).put(j, new Double(mat[i][j]));
								if(!max_heap.get(i).valid() || max_heap.get(i).getValue().compareTo(new Double(mat[i][j]))<0) {
									max_heap.remove(i);
									max_heap.add(i, new MatrixEntry(new Double(mat[i][j]),i,j));
								}
							});
			});
	}
	
	@Deprecated
	public SparseMatrix(AdjMatrix adj) {
		this.sparse_rows = new ArrayList<TreeMap<Integer,Double>>();
		this.max_heap = new ArrayList<MatrixEntry>();
		IntStream.range(0, adj.length()).forEach((i)->{
				sparse_rows.add(new TreeMap<Integer,Double>());
				max_heap.add(MatrixEntry.generateInvalid());
				IntStream.range(0, adj.length())
							.forEach((j)->{
								sparse_rows.get(i).put(j, new Double(adj.getIndex(i, j)));
								if(!max_heap.get(i).valid() || max_heap.get(i).getValue().compareTo(new Double(adj.getIndex(i, j)))<0) {
									max_heap.remove(i);
									max_heap.add(i, new MatrixEntry(new Double(adj.getIndex(i, j)),i,j));
								}
							});
			});
	}
	public double getIndex(int i, int j) {
		
		return sparse_rows.get(i).get(new Integer(j)).doubleValue();
	}
	public MatrixEntry maxOfRow(int i) {
		return max_heap.get(i);
	}
	public MatrixEntry maxEntry() {
		return max_heap.stream().max((a,b)->a.getValue().compareTo(b.getValue())).get();
	}
	public void setIndex(int i, int j, Double v) {
		sparse_rows.get(i).put(new Integer(j), new Double(v));
		if(!max_heap.get(i).valid() || max_heap.get(i).getValue().compareTo(v)<0)
			max_heap.get(i).setValue(v);
	}
	public int length() {
		return sparse_rows.size();
	}
	
	public void removeRowsColumns(int i) {
		sparse_rows.remove(i);
		max_heap.remove(i);
		IntStream.range(0, sparse_rows.size())
					.forEach((row)->sparse_rows.get(row).remove(i));
	}
	public static void main(String[] args) {
		SparseMatrix sm = new SparseMatrix(new int[][] {{1,2,3},{1,2,3},{1,2,3}});
		IntStream.range(0, sm.length()).forEach((i)->IntStream.range(0, sm.length()).forEach((j)->System.out.println(String.format("%d,%d: %f", i,j,sm.getIndex(i, j)))));
		IntStream.range(0, sm.length()).forEach((i)->System.out.println(sm.maxOfRow(i)));
		sm.setIndex(1, 2, 5.0);
		IntStream.range(0, sm.length()).forEach((i)->IntStream.range(0, sm.length()).forEach((j)->System.out.println(String.format("%d,%d: %f", i,j,sm.getIndex(i, j)))));
		IntStream.range(0, sm.length()).forEach((i)->System.out.println(sm.maxOfRow(i)));
	}
}