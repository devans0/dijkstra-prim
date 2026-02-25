/**
 * title: DSPrim GUI
 * @author Dominic Evans
 * @date February 25, 2026
 * @version 1.0
 * @copyright 2026 Dominic Evans
 */

/**
 * This class controls the layout and generation of the GUI window. This is the 
 * primary point of entry into the program from a user interface perspective; the
 * DSPrimGUI class will be called by the main method and all other execution is 
 * coordinated from there via the use of buttons and other GUI elements.
 */

package dsprim.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import dsprim.datastructures.*;
import dsprim.algorithms.GraphSolver;

public class DSPrimGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Graph graph;
	private GraphGUI graphGUI;
	private GraphController controller;
	
	public DSPrimGUI() {
		// Initialize data structure, view, and controller
		graph = new Graph();
		graphGUI = new GraphGUI(graph);
		controller = new GraphController(graph, graphGUI);
		
		// Link the controller to the view
		graphGUI.addMouseListener(controller);
		graphGUI.addMouseMotionListener(controller);
		
		setupWindow();
		
		this.setTitle("Prim's and Dijkstra's Algorithm Visualizer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 800);
		this.setLocationRelativeTo(null);  // Center of screen on open
	}
	
	private void setupWindow() {
		// Window
		this.setLayout(new BorderLayout());
		this.add(graphGUI, BorderLayout.CENTER);
		
		/* Controls */
		JPanel controls = new JPanel();
		JButton btnAddVertex = new JButton("Add Vertex");
		JButton btnDeleteEdge = new JButton("Delete Edge");
		JButton btnDijkstra = new JButton("Run Dijkstra");
		JButton btnPrim = new JButton("Run Prim MST");
		String[] fringeOptions = { "Min-Heap", "Linked List" };
		JComboBox<String> fringeSelect = new JComboBox<>(fringeOptions);
		JButton btnReset = new JButton("Reset");
		
		/* Listeners */
		// Buttons
		btnAddVertex.addActionListener(e -> graphGUI.setVertexMode(true));
		btnDeleteEdge.addActionListener(e -> controller.deleteEdge());
		btnReset.addActionListener(e -> {
			graph.reset();
			graphGUI.repaint();
		});
		// Dijkstra Runner
		btnDijkstra.addActionListener(e -> {
			List<Vertex> selected = graphGUI.getSelectedVertices();
			if (selected.size() == 2) {
				Fringe fringe = getSelectedFringe(fringeSelect);
				if (fringe == null) {
					return;
				}
				GraphSolver solver = new GraphSolver(graph, fringe, selected.get(0), () -> graphGUI.repaint(), true);
				new Thread(solver).start();
			} else {
				JOptionPane.showMessageDialog(this, "Select a Start and End vertex.");
			}
		});
		// Prim MST Runner
		btnPrim.addActionListener(e -> {
			List<Vertex> selected = graphGUI.getSelectedVertices();
			if (selected.size() >= 1) {
				Fringe fringe = getSelectedFringe(fringeSelect);
				GraphSolver solver = new GraphSolver(graph, fringe, selected.get(0), () -> graphGUI.repaint(), false);
				new Thread(solver).start();
			} else {
				JOptionPane.showMessageDialog(this, "Select a starting vertex");
			}
		});
		
		// Add buttons/selectors to the controls panel
		controls.add(btnAddVertex);
		controls.add(btnDeleteEdge);
		controls.add(btnDijkstra);
		controls.add(btnPrim);
		controls.add(new JLabel("Fringe Structure:"));
		controls.add(fringeSelect);
		controls.add(btnReset);
		
		this.add(controls, BorderLayout.SOUTH);
	}
	
	/**
	 * Allows for setting the fringe data structure to use while running the visualization.
	 * 
	 * @param selector UI element that permits choosing data structure.
	 * @return The appropriate Fringe data structure.
	 */
	private Fringe getSelectedFringe(JComboBox<String> selector) {
		if (selector.getSelectedIndex() == 0) {
			return new BinaryMinHeap(graph.getVertexCount());
		} else {
			return new LinkedListMinQueue();
		}
	}
}