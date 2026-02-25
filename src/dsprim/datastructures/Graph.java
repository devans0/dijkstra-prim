/**
 * title: Graph
 * description: Data structure for representing undirected weighted graphs via an adjacency list.
 * @author Dominic Evans
 * @date February 24, 2026
 * @version 1.0
 * @copyright 2026 Dominic Evans
 */

package dsprim.datastructures;

import java.util.Map;
import java.awt.Color;
import java.util.HashMap;

public class Graph {
	private Map<Integer, Vertex> nodes = new HashMap<Integer, Vertex>();
	private int nodeCount = 0;

	public Graph() {}
	
	/**
	 * Adds a new node to the graph at the provided coordinates.
	 * 
	 * @param x the x-coordinate for the new node.
	 * @param y the y-coordinate for the new node.
	 * @return The newly created node.
	 */
	public Vertex addNode(int x, int y) {
		Vertex n = new Vertex(++nodeCount, x, y);
		nodes.put(n.getID(), n);
		return n;
	}

	/**
	 * Adds a new edge to the graph between two extant vertices.
	 * 
	 * @param u      the first vertex in the new edge identified by its integer ID
	 * @param v      the second vertex in the new edge identified by its integer ID
	 * @param weight the weight of the new edge
	 */
	public void addEdge(int u, int v, double weight) {
		Vertex n1 = nodes.get(u);
		Vertex n2 = nodes.get(v);
		if (n1 != null && n2 != null) {
			n1.addEdge(n2, weight);
			n2.addEdge(n1, weight);
		}
	}
	
	/**
	 * Method to reset the graph back to a default state so that the algorithms may
	 * be run on it again.
	 */
	public void reset() {
		for (Vertex n : nodes.values()) {
			n.setDistance(Double.POSITIVE_INFINITY);
			n.setParent(null);
			n.setColour(Color.WHITE);
			// Set edge colour back to default
			for (Edge e : n.getEdges()) {
				e.setColour(Color.WHITE);
			}
		}
	}
}
