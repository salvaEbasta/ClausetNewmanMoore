package greedyModularity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import greedyModularity.graph.Graph;
import greedyModularity.graph.GraphBuilder;
import greedyModularity.graph.Node;
import greedyModularity.greedyModularity.GreedyModularity;

class GreedyModularityTest {

	@Test
	void adj3x3Trivial() {
		int[][] adjMat = new int[][] {{0, 1, 0},
										{1, 0, 0},
										{0, 0, 0}};
		Graph g = GraphBuilder.build(adjMat);
		
		ArrayList<HashSet<Node>> expected = new ArrayList<HashSet<Node>>();
		expected.add(new HashSet<Node>());
		expected.add(new HashSet<Node>());
		expected.get(0).add(new Node("0"));
		expected.get(0).add(new Node("1"));
		expected.get(1).add(new Node("2"));

		List<Set<Node>> result = GreedyModularity.extract(g);
		assertTrue(result.containsAll(expected));
	}
	
	@Test
	void adj7x7() {
		int[][] adjMat = new int[][] {{0,1,1,0,0,0,0},
										{1,0,1,1,0,0,0},
										{1,1,0,1,0,0,0},
										{0,1,1,0,1,0,0},
										{0,0,0,1,0,1,1},
										{0,0,0,0,1,0,1},
										{0,0,0,0,1,1,0}};
		//"[[var_0, var_1, var_2, var_3], 
		//	[var_4, var_5, var_6]]";
		Graph g = GraphBuilder.build(adjMat);
		
		ArrayList<HashSet<Node>> expected = new ArrayList<HashSet<Node>>();
		expected.add(new HashSet<Node>());
		expected.add(new HashSet<Node>());
		expected.get(0).add(new Node("0"));
		expected.get(0).add(new Node("1"));
		expected.get(0).add(new Node("2"));
		expected.get(0).add(new Node("3"));
		expected.get(1).add(new Node("4"));
		expected.get(1).add(new Node("5"));
		expected.get(1).add(new Node("6"));

		List<Set<Node>> result = GreedyModularity.extract(g);
		assertTrue(result.containsAll(expected));
	}

	@Test
	void adj20x20() {
		int[][] adjMat = new int[][] {{0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										{1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
										{0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
										{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
										{0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
										{0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
										{0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1,0,0,0},
										{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1},
										{0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1},
										{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0}};
		// [[var_0, var_1, var_5], [var_2, var_7, var_8], [var_3, var_4, var_9], [var_6, var_12], [var_10, var_11, var_15, var_16], [var_13, var_14, var_18, var_19], [var_17]]
		Graph g = GraphBuilder.build(adjMat);
		
		ArrayList<HashSet<Node>> expected = new ArrayList<HashSet<Node>>();
		expected.add(new HashSet<Node>());
		expected.add(new HashSet<Node>());
		expected.add(new HashSet<Node>());
		expected.add(new HashSet<Node>());
		expected.add(new HashSet<Node>());
		expected.add(new HashSet<Node>());
		expected.add(new HashSet<Node>());
		expected.get(0).add(new Node("0"));
		expected.get(0).add(new Node("1"));
		expected.get(0).add(new Node("5"));
		expected.get(1).add(new Node("2"));
		expected.get(1).add(new Node("7"));
		expected.get(1).add(new Node("8"));
		expected.get(2).add(new Node("3"));
		expected.get(2).add(new Node("4"));
		expected.get(2).add(new Node("9"));
		expected.get(3).add(new Node("6"));
		expected.get(3).add(new Node("12"));
		expected.get(4).add(new Node("10"));
		expected.get(4).add(new Node("11"));
		expected.get(4).add(new Node("15"));
		expected.get(4).add(new Node("16"));
		expected.get(5).add(new Node("13"));
		expected.get(5).add(new Node("14"));
		expected.get(5).add(new Node("18"));
		expected.get(5).add(new Node("19"));
		expected.get(6).add(new Node("17"));
		
		List<Set<Node>> result = GreedyModularity.extract(g);
		assertTrue(result.containsAll(expected));
	}
}
