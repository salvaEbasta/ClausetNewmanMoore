package clausetNewmanMooreAlgorithm_v2;

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
		removeCol(i);
		removeRow(i);
		dim = dim - 1;
	}
	
	private void removeCol(int c) {
		IntStream.range(0, dim)
					.filter((r)->rows.containsKey(r))
					.forEach((r)->{
						if(rows.get(r).containsKey(c))
							rows.get(r).remove(c);
						int prevC = c;
						int nextC = c;
						if(rows.get(r).ceilingKey(prevC) != null)
							nextC = rows.get(r).ceilingKey(prevC);
						while(nextC != prevC && nextC < dim) {
							rows.get(r).put(nextC-1, rows.get(r).get(nextC));
							rows.get(r).remove(nextC);
							prevC = nextC;
							if(rows.get(r).ceilingKey(prevC) != null)
								nextC = rows.get(r).ceilingKey(prevC);
						}
					});
	}
	
	private void removeRow(int r) {
		rows.remove(r);
		int prevR = r;						
		int nextR = r;
		if(rows.ceilingKey(prevR) != null)
			nextR = rows.ceilingKey(prevR);
		while(nextR != prevR && nextR < dim) {
			rows.put(nextR-1, rows.get(nextR));
			rows.remove(nextR);
			prevR = nextR;
			if(rows.ceilingKey(prevR) != null)
				nextR = rows.ceilingKey(prevR);
		}
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
		System.out.println("Test 1: zeroes matrix");
		SparseMatrix r2x2 = new SparseMatrix(2);
		IntStream.range(0, r2x2.size()).forEach((i)->IntStream.range(0, r2x2.size()).forEach((j)->System.out.println(String.format("%d,%d: %f",i,j,r2x2.get(i,j)))));
		
		System.out.println("Test 2: one different than 0 matrix");
		r2x2.set(0,0,2);
		IntStream.range(0, r2x2.size()).forEach((i)->IntStream.range(0, r2x2.size()).forEach((j)->System.out.println(String.format("%d,%d: %f",i,j,r2x2.get(i,j)))));
		System.out.println(r2x2.toString());
		
		System.out.println("Test 3: from one item to all zeroes");
		System.out.println(r2x2.toString());
		r2x2.set(0, 0, 0);
		System.out.println(r2x2.toString());
		System.out.println("Test 4: reduction of one Row&Column");
		r2x2.set(0, 0, 1);
		r2x2.set(0, 1, 2);
		r2x2.set(1, 0, 3);
		r2x2.set(1, 1, 4);
		System.out.println(r2x2.toString());
		r2x2.removeRowCol(0);
		System.out.println(r2x2.toString());
		r2x2.removeRowCol(0);
		System.out.println(r2x2.toString());
	}
}
