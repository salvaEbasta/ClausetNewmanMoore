package greedyModularity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import greedyModularity.graph.Node;
import greedyModularity.graph.SimpleUndirectedGraph;

class GraphTest {

	@Test
	void node_introduction_test() {
		SimpleUndirectedGraph<Node> g = new SimpleUndirectedGraph<Node>();
		assertTrue(g.edgesN()==0);
		assertTrue(g.nodes().size()==0);
		
		Node a = new Node("a");
		Node b = new Node("b");
		g.add_node(a);
		assertTrue(g.edgesN()==0);
		assertTrue(g.nodes().size()==1);
		
		g.add_node(b);
		assertTrue(g.edgesN()==0);
		assertTrue(g.nodes().size()==2);
	}
	
	@Test
	void add_edge_test() {
		SimpleUndirectedGraph<Node> g = new SimpleUndirectedGraph<Node>();
		Node a = new Node("a");
		Node b = new Node("b");
		
		assertFalse(g.add_edge(a, b));
		g.add_node(a);
		assertFalse(g.add_edge(a, b));
		g.add_node(b);
		assertTrue(g.add_edge(a, b));
		assertTrue(g.edgesN()==1);
		assertTrue(g.neighbors(a).contains(b));
		assertTrue(g.neighbors(b).contains(a));
		
		g.add_edge(a, b);
		assertTrue(g.edgesN()==2);
	}
}
