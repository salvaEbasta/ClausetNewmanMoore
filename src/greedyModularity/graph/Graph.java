package greedyModularity.graph;

import java.util.List;

/**
 * Defines the behavior of a generic Graph
 * @author Matteo
 *
 */
public interface Graph <N>{
	/**
	 * @return the list of all the nodes of the graph
	 */
	public List<N> nodes();
	/**
	 * @return the number of ALL the edges of the graph
	 */
	public double edgesN();
	/**
	 * Returns the degree of a specific node
	 * @param node a node of the graph
	 * @return the degree of the node
	 */
	public double degree(N node);
	/**
	 * Returns the neighbors of a specific node
	 * @param node a node of the graph
	 * @return the list of its neighbors
	 */
	public List<N> neighbors(N node);
}
