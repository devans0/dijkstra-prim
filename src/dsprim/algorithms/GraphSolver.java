package dsprim.algorithms;

import dsprim.datastructures.*;
import dsprim.gui.AnimationCallback;

public class GraphSolver implements Runnable {
	private static final int PAUSE_DURATION = 500;  // Use for tuning pauses for GUI drawing

	private Graph graph;
	private Vertex origin;
	private Fringe fringe;
	private boolean isDijkstra;  // true for using Dijkstra; false for Prim
	private AnimationCallback callback;
	
	public GraphSolver(Graph graph, Fringe fringe, Vertex origin, AnimationCallback callback, boolean isDijkstra) {
		this.graph = graph;
		this.fringe = fringe;
		this.origin = origin;
		this.callback = callback;
		this.isDijkstra = isDijkstra;
	} // ctor

	@Override
	public void run() {
		if (callback != null) {
			callback.onRunStart();
		}
		graph.reset();
		origin.setDistance(0);
		origin.setState(Vertex.State.IN_FRINGE);
		fringe.add(origin);
		
		while (!fringe.isEmpty()) {
			Vertex u = fringe.extractMin();
			u.setState(Vertex.State.VISITED);
			
			// Alert the GUI of each step completed and pause to 
			// give it time to update
			if (callback != null) {
				callback.onStepCompleted();
				sleep(PAUSE_DURATION);
			}

			for (Edge e : u.getEdges()) {
				Vertex v = e.getOpposite(u);
				if (v.getState() == Vertex.State.VISITED) {
					continue;
				}
				double newDist = isDijkstra ? (u.getDistance() + e.getWeight()) : e.getWeight();
				if (newDist < v.getDistance()) {
					v.setDistance(newDist);
					v.setParent(u);

					if (v.getState() == Vertex.State.UNVISITED) {
						v.setState(Vertex.State.IN_FRINGE);
						fringe.add(v);
					} else {
						fringe.update(v, newDist);
					}
				}
			}
		}
	} // run
	
	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	} // sleep
}