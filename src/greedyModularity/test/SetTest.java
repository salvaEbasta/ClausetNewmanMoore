package greedyModularity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

class SetTest {

	@Test
	void union_test() {
		int i = 0;
		int j = 1;
		
		TreeMap<Integer, TreeMap<Integer, Double>> dq_dict = new TreeMap<Integer, TreeMap<Integer, Double>>();
		dq_dict.put(i, new TreeMap<Integer, Double>());
		dq_dict.put(j, new TreeMap<Integer, Double>());
		dq_dict.get(i).put(1, 1.0);
		dq_dict.get(i).put(5, 5.0);
		dq_dict.get(i).put(6, 6.0);
		dq_dict.get(j).put(4, 4.0);
		System.out.println(dq_dict.get(i).keySet().getClass().getName());
		
		Set<Integer> i_set = dq_dict.get(i).keySet();
		Set<Integer> j_set = dq_dict.get(j).keySet();
		
		Set<Integer> all_set = new HashSet<Integer>();
		all_set.addAll(i_set);
		all_set.addAll(j_set);
		all_set.remove(i);
		all_set.remove(j);
		Set<Integer> both_set = new HashSet<Integer>(i_set);
		both_set.retainAll(j_set);
		
		assertTrue(all_set.contains(5));
		assertTrue(all_set.contains(6));
		assertTrue(all_set.contains(4));
		assertTrue(all_set.size()==3);
		assertTrue(both_set.size()==0);
	}

}
