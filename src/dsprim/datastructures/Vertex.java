/**
 * title: Node
 * description: Used for representing a vertex of a graph.
 * @author Dominic Evans
 * @date February 24, 2026
 * @version 1.0
 * @copyright 2026 Dominic Evans
 */

package dsprim.datastructures;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class Vertex {
	private int ID;
	private List<Edge> edges;
	private Color colour = Color.WHITE;
	private int x;  // Coordinates for GUI 
	private int y;
	
	// Dijkstra and Prim defaults
	private double distance = Double.POSITIVE_INFINITY;
	private Vertex parent = null;
	
	// Binary Heap bookkeeping
	private int heapIdx;
	
	public Vertex(int ID, int x, int y) {
		this.ID = ID;
		this.x = x;
		this.y = y;
		this.edges = new ArrayList<Edge>();
	} // ctor
	
	/**
	 * Add a new edge to the node
	 * 
	 * @param n the neighbouring node the new edge leads to.
	 * @param w the weight of the new edge.
	 */
	public void addEdge(Vertex n, double w) {
		edges.add(new Edge(n, w, Color.WHITE));
	}

	// Getters and setters
	public int getID() {
		return ID;
	}
	
	public List<Edge> getEdges() {
		return edges;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}
	
	public void setHeapIdx(int idx) {
		heapIdx = idx;
	}
	
	public int getHeapIdx() {
		return heapIdx;
	}
} // class