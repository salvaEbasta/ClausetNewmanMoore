package greedyModularity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import greedyModularity.graph.GraphBuilder;
import greedyModularity.graph.Node;
import greedyModularity.graph.SimpleUndirectedGraph;

class GraphBuilderTest {

	@Test
	void test_3x3() {
		int[][] adjMat = new int[][] {{0, 1, 0},
										{1, 0, 0},
										{0, 0, 0}};
		SimpleUndirectedGraph expected = new SimpleUndirectedGraph();
		expected.add_node(new Node("0"));
		expected.add_node(new Node("1"));
		expected.add_node(new Node("2"));
		expected.add_edge(new Node("0"), new Node("1"));
		
		SimpleUndirectedGraph g = GraphBuilder.build(adjMat);
		assertTrue(g.equals(expected));
	}

	@Test
	void test_7x7() {
		int[][] adjMat = new int[][] {{0,1,1,0,0,0,0},
							            {1,0,1,1,0,0,0},
							            {1,1,0,1,0,0,0},
							            {0,1,1,0,1,0,0},
							            {0,0,0,1,0,1,1},
							            {0,0,0,0,1,0,1},
							            {0,0,0,0,1,1,0}};
		SimpleUndirectedGraph expected = new SimpleUndirectedGraph();
		expected.add_node(new Node("0"));
		expected.add_node(new Node("1"));
		expected.add_node(new Node("2"));
		expected.add_node(new Node("3"));
		expected.add_node(new Node("4"));
		expected.add_node(new Node("5"));
		expected.add_node(new Node("6"));
		
		
		expected.add_edge(new Node("0"), new Node("1"));
		expected.add_edge(new Node("0"), new Node("2"));
		expected.add_edge(new Node("1"), new Node("2"));
		expected.add_edge(new Node("1"), new Node("3"));
		expected.add_edge(new Node("2"), new Node("3"));
		expected.add_edge(new Node("3"), new Node("4"));
		expected.add_edge(new Node("4"), new Node("5"));
		expected.add_edge(new Node("4"), new Node("6"));
		expected.add_edge(new Node("5"), new Node("6"));
		
		SimpleUndirectedGraph g = GraphBuilder.build(adjMat);
		assertTrue(g.equals(expected));
	}

}
