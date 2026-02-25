package dsprim.datastructures;

import java.awt.Color;

public class Edge {
	private Node neighbour;
	private double weight;
	private Color colour = Color.WHITE;
	
	public Edge(Node n, double weight, Color colour) {
		this.neighbour = n;
		this.weight = weight;
		this.colour = colour;
	} // ctor

	// Getters and setters
	public Node getNeighbour() {
		return neighbour;
	}

	public void setNeighbour(Node neighbour) {
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