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
		long buildStart = System.nanoTime();
		
		Graph g = GraphBuilder.build(adjMat);
		
		double buildDuration = (System.nanoTime() - buildStart)/1000000;
		
		ArrayList<HashSet<Node>> expected = new ArrayList<HashSet<Node>>();
		expected.add(new HashSet<Node>());
		expected.add(new HashSet<Node>());
		expected.get(0).add(new Node("0"));
		expected.get(0).add(new Node("1"));
		expected.get(1).add(new Node("2"));

		long methodStart = System.nanoTime();
		
		List<Set<Node>> result = GreedyModularity.extract(g);
		
		double methodDuration = (System.nanoTime() - methodStart)/1000000;
		
		assertTrue(result.containsAll(expected));
		System.out.println("3x3: build: "+buildDuration+" ms, method: "+methodDuration+" ms");
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
		long buildStart = System.nanoTime();
		
		Graph g = GraphBuilder.build(adjMat);
		
		double buildDuration = (System.nanoTime() - buildStart)/1000000;
		
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

		long methodStart = System.nanoTime();
		
		List<Set<Node>> result = GreedyModularity.extract(g);
		
		double methodDuration = (System.nanoTime() - methodStart)/1000000;
		
		assertTrue(result.containsAll(expected));
		System.out.println("7x7: build: "+buildDuration+" ms, method: "+methodDuration+" ms");
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
		
		long buildStart = System.nanoTime();
		
		Graph g = GraphBuilder.build(adjMat);
		
		double buildDuration = (System.nanoTime() - buildStart)/1000000;
		
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
		
		long methodStart = System.nanoTime();
		
		List<Set<Node>> result = GreedyModularity.extract(g);
		
		double methodDuration = (System.nanoTime() - methodStart)/1000000;
		
		assertTrue(result.containsAll(expected));
		System.out.println("20x20: build: "+buildDuration+" ms, method: "+methodDuration+" ms");
	}
}
