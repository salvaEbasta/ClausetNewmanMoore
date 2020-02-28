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
		Edge ab = new Edge(a, b);
		Edge ba = new Edge(b, a);
		assertTrue(Objects.equals(ab, ba));
		
		ab = new Edge(a, b, "ab");
		assertFalse(Objects.equals(ab, ba));
	}
}
