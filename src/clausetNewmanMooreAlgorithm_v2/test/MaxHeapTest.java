package clausetNewmanMooreAlgorithm_v2.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

class MaxHeapTest {

	@Test
	void genericFunciontalityOfPriorityQueue() {
		int i = 1; int j = 2;
		PriorityQueue<Integer> prq = new PriorityQueue<Integer>((i1, i2)->-i1.compareTo(i2));
		prq.add(i);
		prq.add(j);
		Integer peeked = prq.peek();
		assertEquals(new Integer(j), peeked);
	}
	
	
}
