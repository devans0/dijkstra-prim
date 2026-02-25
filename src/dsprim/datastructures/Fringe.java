/**
 * title: Fringe Interface
 * @author Dominic Evans
 * @date February 24, 2026
 * @version 1.0
 * @copyright 2026 Dominic Evans
 */

/**
 * This interface allows for generically programming both Dijkstra's and Prim's algorithms
 * without concern for the implementation of the Min-Queue that each algorithm requires.
 */

package dsprim.datastructures;

public interface Fringe {
	void add(Vertex node);
	Vertex extractMin();
	boolean isEmpty();
	void update(Vertex node, double newCost);
}
