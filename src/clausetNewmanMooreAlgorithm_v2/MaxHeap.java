package clausetNewmanMooreAlgorithm_v2;

import java.util.Iterator;
import java.util.PriorityQueue;

public class MaxHeap {
	private PriorityQueue<MatrixEntry> prq;
	
	public MaxHeap() {
		prq = new PriorityQueue<MatrixEntry>((me1, me2)->-me1.compareTo(me2));	
	}
	
	public MatrixEntry poll() {
		return prq.poll();
	}
	
	public boolean add(MatrixEntry toAdd) {
		Iterator<MatrixEntry> iter = prq.iterator();
		boolean compare = false;
		boolean rowCheck = false;
		MatrixEntry next = null;
		while(iter.hasNext()) {
			next = iter.next();
			if(next.sameRow(toAdd)) {
				rowCheck = true;
				if(next.compareTo(toAdd) < 0) {
					compare = true;
					break;
				}
			}
		}
		if(!rowCheck)
			return prq.add(toAdd);
		else if(compare) {
			prq.remove(next);
			return prq.add(toAdd);
		}
		return false;
	}
	
	public boolean contains(int i, int j) {
		Iterator<MatrixEntry> iter = prq.iterator();
		while(iter.hasNext())
			if(iter.next().hasCoords(i, j))
				return true;
		return false;
	}
	
	public MatrixEntry entryAt(int row) throws Exception{
		throw new Exception();
	}
	
	public boolean removeAt(int row) throws Exception{
		throw new Exception();
	}
	
	public void update(int row) throws Exception{
		throw new Exception();
	}
	
	public Double valueAt(int row) throws Exception{
		throw new Exception();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return prq.toString();
	}
}
