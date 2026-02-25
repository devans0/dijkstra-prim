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
import java.util.Set;
import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collection;

public class Graph {
	private Map<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
	private Set<Edge> edges = new HashSet<Edge>();
	private int vertexCount = 0;

	public Graph() {}
	
	// Getters
	public Collection<Vertex> getVertices() {
		return vertices.values();
	}
	
	public int getVertexCount() {
		return vertices.size();
	}

	public Set<Edge> getEdges() {
		return edges;
	}
	
	/**
	 * Adds a new vertex to the graph at the provided coordinates.
	 * 
	 * @param x the x-coordinate for the new vertex.
	 * @param y the y-coordinate for the new vertex.
	 * @return The newly created vertex.
	 */
	public Vertex addVertex(int x, int y) {
		Vertex n = new Vertex(++vertexCount, x, y);
		vertices.put(n.getID(), n);
		return n;
	}
	
	/**
	 * Deletes a vertex with a given coordinate and radius.
	 * 
	 * @param x The x-coordinate of the vertex.
	 * @param y The y-cooredinate of the vertex.
	 * @param radius The radius of the vertex; this is a property of the GUI
	 */
	public void deleteVertex(int x, int y, int radius) {
		Vertex target = getVertexAt(x, y, radius);
		if (target == null) {
			return;
		}
		for (Edge e : target.getEdges()) {
			Vertex opp = e.getOpposite(target);
			opp.getEdges().remove(e);
			edges.remove(e);
		}
		target.getEdges().clear();
		vertices.remove(target.getID());
	}
	
	// Overload
	public void deleteVertex(Vertex v) {
		for (Edge e : v.getEdges()) {
			Vertex opp = e.getOpposite(v);
			opp.getEdges().remove(e);
			edges.remove(e);
		}
		v.getEdges().clear();
		vertices.remove(v.getID());
	}
	
	/**
	 * Finds the vertex at a particular coordinate with a given radius.
	 * 
	 * @param x The x-cooridnate of the vertex.
	 * @param y The y-coordinate of the vertex.
	 * @param radius The radius of the vertex; this is a property of the GUI.
	 * @return The vertex found; null if no vertex is found.
	 */
	public Vertex getVertexAt(int x, int y, int radius) {
		for (Vertex v : vertices.values()) {
			double dist = Math.sqrt(Math.pow(v.getX() - x, 2) + Math.pow(v.getY() - y, 2));
			if (dist <= radius) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Connects two graph vertices together.
	 * 
	 * @param v1 The first vertex.
	 * @param v2 The second vertex.
	 * @param weight The weight of the edge.
	 * @return The new Edge that connected the two vertices 
	 */
	public Edge connect(Vertex v1, Vertex v2, double weight) {
		Edge e = new Edge(v1, v2, weight, Color.WHITE);
		if (verticesConnected(v1, v2)) {
			return null;
		}
		v1.addEdge(e);
		v2.addEdge(e);
		edges.add(e);
		return e;
	}
	
	/**
	 * Disconnects two vertices by removing their shared edge.
	 * 
	 * @param v1 The first vertex.
	 * @param v2 The second vertex.
	 */
	public void disconnect(Vertex v1, Vertex v2) {
		Edge target = null;
		for (Edge e : v1.getEdges()) {
			if (e.getOpposite(v1) == v2) {
				target = e;
				break;
			}
		}
		if (target != null) {
			v1.getEdges().remove(target);
			v2.getEdges().remove(target);
			edges.remove(target);
		}
	}
	
	/**
	 * Determines if two provided vertices share an edge.
	 * 
	 * @param v1 The first vertex.
	 * @param v2 The second vertex.
	 * @return true of the vertices share an edge; false otherwise.
	 */
	private boolean verticesConnected(Vertex v1, Vertex v2) {
		for (Edge e : v1.getEdges()) {
			if (e.getOpposite(v1) == v2) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to reset the graph back to a default state so that the algorithms may
	 * be run on it again.
	 */
	public void reset() {
		for (Vertex n : vertices.values()) {
			n.setDistance(Double.POSITIVE_INFINITY);
			n.setParent(null);
			n.setColor(Color.WHITE);
			n.setVisited(false);
			// Set edge color back to default
			for (Edge e : n.getEdges()) {
				e.setColor(Color.WHITE);
			}
		}
	}
}
