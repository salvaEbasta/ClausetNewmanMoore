package greedyModularity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import greedyModularity.graph.Node;


class NodeTest {

	@Test
	void equality_test() {
		Node a = new Node("a");
		assertTrue(a.equals(new Node("a")));
	}

}
