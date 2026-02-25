/**
 * title: Graph Controller
 * @author Dominic Evans
 * @date February 25, 2026
 * @version 1.0
 * @copyright 2026 Dominic Evans 
 */

/**
 * This class is responsible for handling events when the user interacts with the view of the 
 * graph using their mouse. It registers changes and updates the state of the GUI so that the
 * underlying Graph data structure can be appropriately updated/built and viewed.
 */

package dsprim.gui;

import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import dsprim.datastructures.*;

public class GraphController extends MouseAdapter {
	private Graph graph;
	private GraphGUI view;

	public GraphController(Graph graph, GraphGUI view) {
		this.graph = graph;
		this.view = view;
	}

	/**
	 * Catches mouse clicks and deals with them appropriately. If the user has
	 * entered "vertex mode" indicating that a new vertex is to be placed, the click
	 * creates that vertex.
	 * 
	 * If the user is in normal mode then the click is a selecting click if it
	 * corresponds to a vertex.
	 * 
	 * If the user is in normal mode and clicks somewhere that is not a vertex, then
	 * the selection is cleared.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		Vertex clicked = graph.getVertexAt(e.getX(), e.getY(), GraphGUI.RADIUS);

		if (view.isVertexMode()) {
			// Click to create
			// User has indicated that a new vertex is to be created
			if (clicked == null) {
				graph.addVertex(e.getX(), e.getY());
				view.setVertexMode(false); // Creating a vertex exits vertex mode
			}
		} else if (clicked != null) {
			// Click to select
			view.toggleSelection(clicked);
			view.setDragSource(clicked);
		} else {
			// Click to clear
			view.getSelectedVertices().clear();
		}
		view.repaint();
	}

	/**
	 * Enables "rubber band" edge creation by clicking and dragging between two
	 * vertices.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (view.getDragSource() != null) {
			view.setMousePos(e.getPoint());
			view.repaint();
		}
	}
	
	/**
	 * Catches click up events. Primarily of interest to manage the rubber band
	 * state which draws a line stretching from an origin vertex to the mouse so
	 * long as the mouse is held down. When the mouse is released, the coordinates
	 * of the mouse are used to determine whether an edge should be drawn.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		Vertex source = view.getDragSource();
		Vertex target = graph.getVertexAt(e.getX(), e.getY(), GraphGUI.RADIUS);
		
		if (source != null && target != null && source != target) {
			String input = JOptionPane.showInputDialog(view, "Enter edge weight:");
			if (input != null && !input.isEmpty()) {
				try {
					double weight = Double.parseDouble(input);
					graph.connect(source, target, weight);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(view,  "Weight must be a number.");
				}
			}
		}
		
		// Clean up the rubber band
		view.setDragSource(null);
		view.setMousePos(null);
		view.repaint();
	}
	
	/**
	 * Deletes any selected vertices. Used by the "Delete Vertex" button.
	 */
	public void deleteHighlighted() {
		List<Vertex> selected = view.getSelectedVertices();
		
		if (selected.size() == 2) {
			graph.disconnect(selected.get(0), selected.get(1));
		}
		
		for (Vertex v : selected) {
			graph.deleteVertex(v);
		}
		
		selected.clear();
		view.repaint();
	}
	
	/**
	 * Deletes an edge shared by two selected vertices. Mean to be called from
	 * the "Delete Edge" button.
	 */
	public void deleteEdge() {
		List<Vertex> selected = view.getSelectedVertices();
		
		// Guard, but the button shouldn't permit this to happen
		if(selected.size() != 2) {
			return;
		}
		graph.disconnect(selected.get(0), selected.get(1));
		selected.clear();
		view.repaint();
	}
}