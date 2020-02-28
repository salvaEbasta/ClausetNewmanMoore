package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import graph.Graph;
import graph.GraphBuilder;
import graph.Node;
import greedyModularity.GreedyModularity;

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

}
