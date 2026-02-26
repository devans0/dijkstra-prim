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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import dsprim.datastructures.*;
import dsprim.algorithms.GraphSolver;

public class DSPrimGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Graph graph;
	private GraphGUI graphGUI;
	private GraphController controller;
	
	// UI elements
	private JPanel controls;
	private JToggleButton btnAddVertex;
	private JButton btnDeleteEdge;
	private JButton btnDijkstra;
	private JButton btnPrim;
	private String[] fringeOptions;
	private JComboBox<String> fringeSelect;
	private JButton btnReset;
	private JButton btnClear;
	private JCheckBox chkRecord;
	
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
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	private void setupWindow() {
		// Window
		this.setLayout(new BorderLayout());
		this.add(graphGUI, BorderLayout.CENTER);
		
		// Key binding
		InputMap im = graphGUI.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = graphGUI.getActionMap();
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelVertexMode");
		am.put("cancelVertexMode", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAddVertex.setSelected(false);
				graphGUI.setVertexMode(false);
				graphGUI.repaint();
			}
		});
		
		/* Controls */
		controls = new JPanel();
		btnAddVertex = new JToggleButton("Add Vertex");
		btnDeleteEdge = new JButton("Delete Edge");
		btnDijkstra = new JButton("Run Dijkstra");
		btnPrim = new JButton("Run Prim MST");
		fringeOptions = new String[]{ "Min-Heap", "Linked List" };
		fringeSelect = new JComboBox<>(fringeOptions);
		btnReset = new JButton("Reset");
		btnClear = new JButton("Clear");
		chkRecord = new JCheckBox("Record Run");
		chkRecord.setToolTipText("Saves each step as an image in the output_frames directory");
		
		/* Listeners */
		// Buttons
		btnAddVertex.addActionListener(e -> graphGUI.setVertexMode(btnAddVertex.isSelected()));
		btnDeleteEdge.addActionListener(e -> controller.deleteEdge());
		btnClear.addActionListener(e -> graph.clear());
		btnDijkstra.addActionListener(e -> runDijkstra(fringeSelect));
		btnPrim.addActionListener(e -> runPrim(fringeSelect));
		btnReset.addActionListener(e -> resetGraph());
		btnClear.addActionListener(e -> clearCanvas());
		chkRecord.addActionListener(e -> {
			graphGUI.setRecording(chkRecord.isSelected());
		});
		
		// Add buttons/selectors to the controls panel
		controls.add(btnAddVertex);
		controls.add(btnDeleteEdge);
		controls.add(btnDijkstra);
		controls.add(btnPrim);
		controls.add(new JLabel("Fringe Structure:"));
		controls.add(fringeSelect);
		controls.add(btnReset);
		controls.add(btnClear);
		controls.add(chkRecord);
		
		this.add(controls, BorderLayout.SOUTH);
	}
	
	/**
	 * Executes Dijkstra's algorithm via the GraphSolver.
	 * 
	 * @param fringeSelect the selected fringe data structure.
	 */
	private void runDijkstra(JComboBox<String> fringeSelect) {
		List<Vertex> selected = graphGUI.getSelectedVertices();
		if (selected.size() == 2) {
			Fringe fringe = getSelectedFringe(fringeSelect);
			if (fringe == null) {
				return;
			}
			GraphSolver solver = new GraphSolver(graph, fringe, selected.get(0), graphGUI, true);
			new Thread(solver).start();
		} else {
			JOptionPane.showMessageDialog(this, "Select a Start and End vertex.");
		}
	}
	
	/**
	 * Executes Prim's MST algorithm via the GraphSolver.
	 * 
	 * @param fringeSelect the selected fringe data structure.
	 */
	private void runPrim(JComboBox<String> fringeSelect) {
		List<Vertex> selected = graphGUI.getSelectedVertices();
		if (selected.size() >= 1) {
			Fringe fringe = getSelectedFringe(fringeSelect);
			GraphSolver solver = new GraphSolver(graph, fringe, selected.get(0), graphGUI, false);
			new Thread(solver).start();
		} else {
			JOptionPane.showMessageDialog(this, "Select a starting vertex");
		}
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
	
	/**
	 * Resets the graph to its original state. All vertices and edges are unvisited,
	 * no vertices have parents, distance for all vertices is +INFTY
	 */
	private void resetGraph() {
		graph.reset();
		graphGUI.repaint();
	}
	
	/**
	 * Clears the canvas by removing all vertices and edges from the graph.
	 */
	private void clearCanvas() {
		graph.clear();
		graphGUI.repaint();
	}
}