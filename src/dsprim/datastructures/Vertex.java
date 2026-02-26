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
	private boolean visited;
	private List<Edge> edges;
	private int x;  // Coordinates for GUI 
	private int y;
	
	// Dijkstra and Prim defaults
	private double distance = Double.POSITIVE_INFINITY;
	private Vertex parent = null;
	
	// Binary Heap bookkeeping
	private int heapIdx;
	
	// Track the state of the vertex during Dijkstra/Prim solve
	public enum State { UNVISITED, IN_FRINGE, VISITED }
	private State currentState = State.UNVISITED;
	
	public Vertex(int ID, int x, int y) {
		this.ID = ID;
		this.x = x;
		this.y = y;
		this.edges = new ArrayList<Edge>();
		visited = false;
	} // ctor
	
	/**
	 * Adds a new edge to the vertex's adjacency list.
	 * 
	 * @param e the new edge to add to the adjacency list
	 */
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	// Getters and setters
	public int getID() {
		return ID;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public List<Edge> getEdges() {
		return edges;
	}

	public Color getColor() {
		switch (currentState) {
		case VISITED:
			return Color.ORANGE;
		case IN_FRINGE:
			return Color.CYAN;
		default:
			return Color.LIGHT_GRAY;
		}
	}
	
	public State getState() {
		return this.currentState;
	}
	
	public void setState(State s) {
		this.currentState = s;
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
} 