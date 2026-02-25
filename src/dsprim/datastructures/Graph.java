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
	private Map<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
	private int vertexCount = 0;

	public Graph() {}
	
	/**
	 * Adds a new node to the graph at the provided coordinates.
	 * 
	 * @param x the x-coordinate for the new node.
	 * @param y the y-coordinate for the new node.
	 * @return The newly created node.
	 */
	public Vertex addNode(int x, int y) {
		Vertex n = new Vertex(++vertexCount, x, y);
		vertices.put(n.getID(), n);
		return n;
	}

	/**
	 * Connects two graph vertices together.
	 * 
	 * @param v1 The first vertex.
	 * @param v2 The second vertex.
	 * @param weight The weight of the edge.
	 */
	public void connect(Vertex v1, Vertex v2, double weight) {
		Edge e = new Edge(v1, v2, weight, Color.WHITE);
		v1.addEdge(e);
		v2.addEdge(e);
	}
	
	/**
	 * Method to reset the graph back to a default state so that the algorithms may
	 * be run on it again.
	 */
	public void reset() {
		for (Vertex n : vertices.values()) {
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
