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
import dsprim.datastructures.Graph;

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
		
		// Controls
		JPanel controls = new JPanel();
		JButton btnAddVertex = new JButton("Add Vertex");
		JButton btnDeleteEdge = new JButton("Delete Edge");
		
		btnAddVertex.addActionListener(e -> graphGUI.setVertexMode(true));
		btnDeleteEdge.addActionListener(e -> controller.deleteEdge());
		
		controls.add(btnAddVertex);
		controls.add(btnDeleteEdge);
		
		this.add(controls, BorderLayout.SOUTH);
	}
}