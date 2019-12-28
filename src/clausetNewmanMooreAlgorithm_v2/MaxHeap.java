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
	
	public boolean populate(MatrixEntry memeber) {
		Iterator<MatrixEntry> iter = prq.iterator();
		boolean compare = false;
		boolean rowCheck = false;
		MatrixEntry next = null;
		while(iter.hasNext()) {
			next = iter.next();
			if(next.sameRow(memeber)) {
				rowCheck = true;
				if(next.compareTo(memeber) < 0) {
					compare = true;
					break;
				}
			}
		}
		if(!rowCheck)
			return prq.add(memeber);
		else if(compare) {
			prq.remove(next);
			return prq.add(memeber);
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
	
	public void removeAt(int row) {
		Iterator<MatrixEntry> iter = prq.iterator();
		MatrixEntry next = null;
		while(iter.hasNext()) {
			next = iter.next();
			if(next.row() == row)
				iter.remove();
			else {
				if(next.row() > row)
					next.decrementRow();
				if(next.column() > row)
					next.decrementColumn();
			}
		}
	}
	
	public boolean update(MatrixEntry toAdd) {
		Iterator<MatrixEntry> iter = prq.iterator();
		MatrixEntry next = null;
		while(iter.hasNext()) {
			next = iter.next();
			if(next.sameRow(toAdd))
				break;
		}
		prq.remove(next);
		return prq.add(toAdd);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return prq.toString();
	}
}
