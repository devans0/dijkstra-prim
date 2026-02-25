/**
 * title: Graph Edge
 * description: Represents an undirected edge in a graph between two vertices
 * @author Dominic Evans
 * @date February 24, 2026
 * @version 1.0
 * @copyright 2026 Dominic Evans
 */

package dsprim.datastructures;

import java.awt.Color;

public class Edge {
	private Vertex u;
	private Vertex v;
	private double weight;
	private Color colour = Color.WHITE;
	
	public Edge(Vertex u, Vertex v, double weight, Color colour) {
		this.u = u;
		this.v = v;
		this.weight = weight;
		this.colour = colour;
	} // ctor

	// Getters and setters
	public Vertex getOpposite(Vertex curr) {
		return (curr == u) ? v : u;
	}

	public void setU(Vertex u) {
		this.u = u;
	}
	
	public void setV(Vertex v) {
		this.v = v;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}
} // class