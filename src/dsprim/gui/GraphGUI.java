/**
 * title: Graph GUI
 * @author Dominic Evans
 * @date February 25, 2026
 * @version 1.0
 * @copyright 2026 Dominic Evans
 */

/**
 * This class implements the Model-View-Controller pattern to create and display the graph, 
 * and for animating it being solved by the desired algorithm.
 */

package dsprim.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import dsprim.datastructures.*;

public class GraphGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private Graph graph;
	public static final int RADIUS = 15; // Use to adjust radius of the drawn vertices
	private List<Vertex> selectedVertices = new ArrayList<>();
	private Vertex dragSource = null;
	private Point mousePos = null;
	private boolean isVertexMode = false;

	public GraphGUI(Graph graph) {
		this.graph = graph;
		this.setBackground(Color.DARK_GRAY);
	} // ctor

	// Setters
	public void setDragSource(Vertex v) {
		dragSource = v;
	}

	public void setMousePos(Point p) {
		mousePos = p;
	}
	
	public void setVertexMode(boolean vertexMode) {
		isVertexMode = vertexMode;
	}
	
	// Getters
	public List<Vertex> getSelectedVertices() {
		return selectedVertices;
	}
	
	public boolean isVertexMode() {
		return isVertexMode;
	}
	
	public Vertex getDragSource() {
		return dragSource;
	}

	public void toggleSelection(Vertex v) {
		if (selectedVertices.contains(v)) {
			selectedVertices.remove(v);
		} else {
			if (selectedVertices.size() < 2) {
				selectedVertices.add(v);
			} else {
				selectedVertices.remove(0);
				selectedVertices.add(v);
			}
		}
	}

	/**
	 * Method for painting the components of the graph to the canvas.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Anti-aliasing
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Line thickness
		g2.setStroke(new BasicStroke(2));

		/*
		 * Draw the edges first so that they lie under the vertices. This avoids any
		 * issues of edges extending beyond the borders of a vertex
		 */
		// Draw edges
		for (Edge e : graph.getEdges()) {
			Vertex u = e.getU();
			Vertex v = e.getV();

			g2.setColor(e.getColor());
			g2.drawLine(u.getX(), u.getY(), v.getX(), v.getY());

			// Weight label
			int midX = (u.getX() + v.getX()) / 2;
			int midY = (u.getY() + v.getY()) / 2;
			String weightStr = String.valueOf((int) e.getWeight());
			g2.setColor(new Color(40, 40, 40, 200));
			g2.fillRect(midX - 10, midY - 10, 20, 15);
			g2.setColor(Color.WHITE);
			g2.drawString(weightStr, midX - 5, midY + 2);
		}

		// Drawing edge creation via click-and-drag
		if (dragSource != null && mousePos != null) {
			g2.setColor(Color.LIGHT_GRAY);
			g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0));
			g2.drawLine(dragSource.getX(), dragSource.getY(), mousePos.x, mousePos.y);
		}

		// Draw vertices
		for (Vertex v : graph.getVertices()) {
			// Change draw values based on selected status
			boolean isSelected = selectedVertices.contains(v);
			BasicStroke stroke = isSelected ? new BasicStroke(3) : new BasicStroke(2);
			Color circleColor = isSelected ? Color.CYAN : v.getColor();
			Color borderColor = isSelected ? Color.WHITE : Color.BLACK;

			// Circle
			g2.setColor(circleColor);
			g2.fillOval(v.getX() - RADIUS, v.getY() - RADIUS, RADIUS * 2, RADIUS * 2);

			// Border
			g2.setColor(borderColor);
			g2.setStroke(stroke);
			g2.drawOval(v.getX() - RADIUS, v.getY() - RADIUS, RADIUS * 2, RADIUS * 2);

			// Paint ID or distance if set
			g2.setColor(Color.BLACK);
			String label = (v.getDistance() == Double.POSITIVE_INFINITY) ? "ID:" + v.getID()
					: String.format("%.1f", v.getDistance());
			g2.drawString(label, v.getX() - (RADIUS * 0.66f), v.getY() + (RADIUS * 0.33f));
		}

	}
}