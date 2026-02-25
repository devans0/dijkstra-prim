/**
 * Title: Binary Min Heap
 * @author Dominic Evans
 * @date February 24, 2026
 * @version 1.0
 * @copyright 2026 Dominic Evans
 */

/**
 * This Binary Min Heap utilizes the array representation of a binary tree to order
 * all of its Nodes in ascending order. Adding a node will place it in the spot within 
 * the heap such that it is greater than its parent and less than its children.
 * 
 * The metric for comparison between nodes is their cumulative edge cost between the
 * node and the origin node. This value is subject to change so the heap is capable of
 * reordering itself when this occurs via the update method.
 */

package dsprim.datastructures;

public class BinaryMinHeap implements Fringe {
	private Vertex[] vertexHeap;
	private int capacity;
	private int size;
	
	// Initializes a new heap of capacity n
	public BinaryMinHeap(int n) {
		this.capacity = n;
		this.vertexHeap = new Vertex[capacity];
		size = 0;
	} // ctor

	/**
	 * Adds a new element to the heap and maintains the min-heap property.
	 */
	@Override
	public void add(Vertex node) {
		if (size == capacity) {
			return;
		}
		int i = size;
		vertexHeap[i] = node;
		vertexHeap[i].setHeapIdx(i);
		size++;
		
		while (i != 0 && vertexHeap[i].getDistance() < vertexHeap[parent(i)].getDistance()) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	/**
	 * Removes the minimum element from the heap.
	 */
	@Override
	public Vertex extractMin() {
		if (size <= 0) {
			return null;
		}

		Vertex root = vertexHeap[0];
		root.setHeapIdx(-1);  // Sentinel: indicates that the node is not in the heap

		// Case of only a single element in the heap
		if (size == 1) {
			size--;
			return root;
		}

		vertexHeap[0] = vertexHeap[size - 1];
		vertexHeap[0].setHeapIdx(0);
		size--;

		enforceMinHeap(0);
		return root;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void update(Vertex node, double newDist) {
		if (node == null) {
			return;
		}
		double oldDist = node.getDistance();
		node.setDistance(newDist);
		if (oldDist > newDist) {
			bubbleUp(node.getHeapIdx());
		} else if (oldDist < newDist) {
			enforceMinHeap(node.getHeapIdx());
		}
	}
	
	/**
	 * Ensures that the heap is in proper order by recursively enforcing the min-heap
	 * property on a subtree rooted at the given element.
	 * 
	 * @param key the key to start at
	 */
	private void enforceMinHeap(int key) {
		int left = left(key);
		int right = right(key);
		int min = key;
		
		if (left < size && vertexHeap[left].getDistance() < vertexHeap[min].getDistance()) {
			min = left;
		}
		if (right < size && vertexHeap[right].getDistance() < vertexHeap[min].getDistance()) {
			min = right;
		}
		if (min != key) {
			swap(key, min);
			enforceMinHeap(min);
		}
	}
	
	/**
	 * Checks that the node at a given element is in the right spot in the heap relative to
	 * its parents.
	 * 
	 * @param key the starting element.
	 */
	private void bubbleUp(int key) {
		while (key > 0 && vertexHeap[parent(key)].getDistance() > vertexHeap[key].getDistance()) {
			swap(key, parent(key));
			key = parent(key);
		}
	}
	
	/*
	 * Helper methods. Used for swapping elements on the heap, finding left and 
	 * right children and finding parent nodes within the array representation.
	 */
	private void swap(int i, int j) {
		Vertex temp = vertexHeap[i];
		vertexHeap[i] = vertexHeap[j];
		vertexHeap[j] = temp;
		
		// Update each node's stored index
		vertexHeap[i].setHeapIdx(i);
		vertexHeap[j].setHeapIdx(j);
	}
	
	private int parent(int key) {
		return (key - 1) / 2;
	}
	
	private int left (int key) {
		return (2 * key) + 1;
	}
	
	private int right (int key) {
		return (2 * key) + 2;
	}
}
