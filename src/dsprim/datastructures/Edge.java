/**
 * title: Graph Edge
 * description: Represents an edge in a graph between two vertices
 * @author Dominic Evans
 * @date February 24, 2026
 * @version 1.0
 * @copyright 2026 Dominic Evans
 */

package dsprim.datastructures;

import java.awt.Color;

public class Edge {
	private Vertex neighbour;
	private double weight;
	private Color colour = Color.WHITE;
	
	public Edge(Vertex n, double weight, Color colour) {
		this.neighbour = n;
		this.weight = weight;
		this.colour = colour;
	} // ctor

	// Getters and setters
	public Vertex getNeighbour() {
		return neighbour;
	}

	public void setNeighbour(Vertex neighbour) {
		this.neighbour = neighbour;
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