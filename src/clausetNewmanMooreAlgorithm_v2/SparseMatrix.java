package clausetNewmanMooreAlgorithm_v2;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class SparseMatrix {
	private TreeMap<Integer,TreeMap<Integer,Double>> rows;
	private int dim;
	
	public SparseMatrix(int dim) {
		this.rows = new TreeMap<Integer,TreeMap<Integer,Double>>();
		this.dim = dim;
	}
	
	public int size() {return dim;}
	public double get(int x, int y) throws IndexOutOfBoundsException{
		if(x>=dim || y>=dim)
			throw new IndexOutOfBoundsException();
		else if(!rows.containsKey(x))
			return 0.0;
		else if(!rows.get(x).containsKey(y))
			return 0.0;
		return rows.get(x).get(y).doubleValue();
	}
	public void set(int x, int y, double value) throws IndexOutOfBoundsException {
		if(x>=dim || y>=dim)
			throw new IndexOutOfBoundsException();
		else if(value!=0.0) {
			if(!rows.containsKey(x))
				rows.put(x, new TreeMap<Integer,Double>());
			rows.get(x).put(y, value);
		}else {
			if(rows.containsKey(x) && rows.get(x).containsKey(y))
				rows.get(x).remove(y);
		}
	}
	public void removeRowCol(int i) throws IndexOutOfBoundsException {
		if(i>=dim)
			throw new IndexOutOfBoundsException();
		IntStream.range(0, dim)
					.forEach((row)->{
						if(rows.containsKey(row)) {
							if(rows.get(row).containsKey(i))
								rows.get(row).remove(i);
							ArrayList<Integer> toMod = new ArrayList<Integer>();
							rows.get(row)
								.keySet()
								.stream()
								.filter((k)->k>i)
								.mapToInt((k)->k.intValue())
								.forEach((k)->toMod.add(k));
							toMod.stream()
									.forEach((k)->{
										double value = rows.get(row).get(k);
										rows.get(row).put(k-1, value);
									});
						}
						rows.get(row).remove(dim-1);
					});
		rows.remove(i);
		ArrayList<Integer> toMod = new ArrayList<Integer>();
		rows.keySet()
			.stream()
			.filter((row)->row>i)
			.forEach((row)->toMod.add(row));
		toMod.stream()
				.forEach((row)->{
					TreeMap<Integer,Double> tmp = rows.get(row);
					rows.put(row-1, tmp);
				});
		rows.remove(dim-1);
		dim = dim - 1;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		rows.keySet().stream().forEach((i)->rows.get(i).keySet().stream().forEach((j)->sb.append(String.format("(%d, %d): %f, ", i, j, rows.get(i).get(j)))));
		return sb.append("]").toString();
	}
	
	public static void main(String[] args) {
		SparseMatrix r2x2 = new SparseMatrix(2);
		IntStream.range(0, r2x2.size()).forEach((i)->IntStream.range(0, r2x2.size()).forEach((j)->System.out.println(String.format("%d,%d: %f",i,j,r2x2.get(i,j)))));
		
		r2x2.set(0,0,2);
		IntStream.range(0, r2x2.size()).forEach((i)->IntStream.range(0, r2x2.size()).forEach((j)->System.out.println(String.format("%d,%d: %f",i,j,r2x2.get(i,j)))));
		System.out.println(r2x2.toString());
		
		r2x2.set(0, 0, 0);
		System.out.println(r2x2.toString());
		r2x2.set(0, 0, 1);
		r2x2.set(0, 1, 2);
		r2x2.set(1, 0, 3);
		r2x2.set(1, 1, 4);
		System.out.println(r2x2.toString());
		r2x2.removeRowCol(0);
		System.out.println(r2x2.toString());
	}
}
