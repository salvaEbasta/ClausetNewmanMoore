package clausetNewmanMooreAlgorithm_v2;

import java.util.Iterator;
import java.util.PriorityQueue;

public class MaxHeap {
	private PriorityQueue<MatrixEntry> prq;
	
	public MaxHeap() {
		prq = new PriorityQueue<MatrixEntry>((me1, me2)->-me1.compareTo(me2));	
	}
	
	public MatrixEntry peek() {
		return prq.peek();
	}
	
	public boolean add(MatrixEntry me) throws Exception{
		throw new Exception();
//		if(entryAt(me.row())==null)
//			return prq.add(me);
//		else {
//			
//		}
//		return false;
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
