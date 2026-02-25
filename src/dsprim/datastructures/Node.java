package dsprim.datastructures;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class Node {
	private int ID;
	private List<Edge> edges;
	private Color colour = Color.WHITE;
	private int x;  // Coordinates for GUI 
	private int y;
	
	// Dijkstra and Prim defaults
	private double distance = Double.POSITIVE_INFINITY;
	private Node parent = null;
	
	public Node(int ID, int x, int y) {
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
	public void addEdge(Node n, double w) {
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

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
} // class