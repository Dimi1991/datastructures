package lists;

/**
 * This class represents an element of a list. A node contains only a reference value of a generic type and 
 * a reference to another node.
 * 
 * @author Dimitri
 *
 * @param <T>
 */
class Node<T> {

	private Node<T> node;
	private T value;
	
	Node<T> getNode() {
		return this.node;
	}
	
	T getValue() {
		return this.value;
	}
	
	/**
	 * @param newValue A value to store.
	 */
	Node(T newValue) {
		this.node = null;
		this.value = newValue;
	}
	
	void setValue(T value) {
		this.value = value;
	}
	
	void setNode(Node<T> node) {
		this.node = node;
	}
}
