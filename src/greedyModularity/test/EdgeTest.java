package greedyModularity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import greedyModularity.graph.Edge;
import greedyModularity.graph.Node;

class EdgeTest {

	@Test
	void equality_test() {
		Node a = new Node("a");
		Node b = new Node("b");
		Edge<Node> ab = new Edge<Node>(a, b);
		Edge<Node> ba = new Edge<Node>(b, a);
		assertTrue(Objects.equals(ab, ba));
		
		ab = new Edge<Node>(a, b, "ab");
		assertFalse(Objects.equals(ab, ba));
	}
}
