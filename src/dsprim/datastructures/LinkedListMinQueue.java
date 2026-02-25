/**
 * title: Linked List Minimum Queue
 * @author Dominic Evans
 * @date February 24, 2026
 * @version 1.0
 * @copyright 2026 Dominic Evans
 */

/**
 * Implements a Minimum Priority Queue using a doubly-linked list. This allows
 * for efficient node updates but requires linear time to find the next vertex
 * with the shortest distance from the origin.
 */

package dsprim.datastructures;

public class LinkedListMinQueue implements Fringe {
	
	// The links for the list
	private static class Link<T> {
		private Link<T> next;
		private Link<T> prev;
		private T element;
		
		private Link(T element) {
			this.element = element;
		}
	}
	
	private Link<Vertex> head = null;
	private Link<Vertex> tail = null;
	private int size;
	
	
	public LinkedListMinQueue() {
		size = 0;
	}

	/**
	 * Simply push new elements on to the head of the list
	 */
	@Override
	public void add(Vertex node) {
		Link<Vertex> newElement = new Link<Vertex>(node);
		if (head == null) {
			head = newElement;
			tail = newElement;
		} else {
			newElement.next = head;
			head.prev = newElement;
			head = newElement;
		}
		size++;
	}

	/**
	 * Scans the entire list and returns the Link holding the vertex with the
	 * smallest distance value.
	 */
	@Override
	public Vertex extractMin() {
		// Case where the list is empty
		if (head == null) {
			return null;
		}
		double minDist = Double.POSITIVE_INFINITY;
		Link<Vertex> curr = head;
		Link<Vertex> min = null;
		// Case where there is only one element in the list
		if (curr == tail) {
			head = null; 
			tail = null;
			size--;
			return curr.element;
		}
		// Scan the list and find the minimum element
		while (curr != null) {
			if (curr.element.getDistance() < minDist) {
				minDist = curr.element.getDistance();
				min = curr;
			}
			curr = curr.next;
		}

		// Remove the min element
		if (min.prev != null) {
			min.prev.next = min.next;
		} else {
			head = min.next;
		}
		
		if (min.next != null) {
			min.next.prev = min.prev;
		} else {
			tail = min.prev;
		}
		size--;

		min.next = null;
		min.prev = null;
		return min.element;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void update(Vertex node, double newDist) {
		node.setDistance(newDist);
	}

}
