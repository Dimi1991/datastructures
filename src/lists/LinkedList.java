package lists;

import java.util.NoSuchElementException;

/**
 * This collection consists of a buckets storing the specified data. It delivers
 * methods to add, insert, delete and retrieve data. This is a recursive
 * datastructure. Bestcase runtime is O(n)=1. Worstcase is O(n)=n. And
 * averagecase is O(n)=n/2.
 * 
 * @author Dimitri Khodak
 *
 * @param <T>
 */
public class LinkedList<T> implements Iterable<T> {

	private Node<T> start = null;
	private Node<T> next;
	
	Node<T> getStart(){
		return this.start;
	}
	
	Node<T> getNext(){
		return this.next;
	}
	
	void setNext(Node<T> nextNode){
		this.next = nextNode;
	}
//	void setStart(Node<T> startNode) {
//		this.start = startNode;
//	}

	/**
	 * This method adds a new value of the list at the very end of the list or at
	 * the beginning, if the list is empty.
	 * 
	 * @param value The Value to add to the list.
	 */
	public void add(T value) {
		if (this.start == null)
			this.start = new Node<T>(value);
		else
			addfurther(value, this.start);
	}

	/**
	 * Recursive Method from add(T value). Is used repeated to iterate until the
	 * last Node and add a new node with the specified value.
	 * 
	 * @param value
	 * @param currentNode
	 */
	private void addfurther(T value, Node<T> currentNode) {
		if (currentNode.getNode() == null)
			currentNode.setNode(new Node<T>(value));
		else
			addfurther(value, currentNode.getNode());
	}

	/**
	 * This method deletes all entries of the list.
	 */
	public void clear() {
		this.start = null;
	}

	/**
	 * This method checks whether the list contains the specified value.
	 * 
	 * @param value The value to check for existence in the list.
	 * @return True, if list contains value. False otherwise.
	 */
	public boolean contains(T value) {
		if (start == null)
			return false;
		if (start.getValue().equals(value))
			return true;
		else
			return containsFurther(value, start);
	}

	/**
	 * Recursive method of contains(T value). Checks whether next node of the
	 * specified node is null or contains the specified value.
	 * 
	 * @param value    The value to check if it is available in the list.
	 * @param currNode The node that was checked before starting this method.
	 * @return Returns false, if value not available. Otherwise true.
	 */
	private boolean containsFurther(T value, Node<T> currNode) {
		if (currNode.getNode() == null)
			return false;
		if (currNode.getNode().getValue().equals(value))
			return true;
		else
			return containsFurther(value, currNode.getNode());
	}

	/**
	 * Retrieves the very first element of the list.
	 * 
	 * @return Returns either the first element of the list or null if the list is
	 *         empty.
	 */
	public T getFirst() {
		if (start != null)
			return start.getValue();
		else
			throw new NoSuchElementException();
	}

	/**
	 * Retrieves the very last element of the list.
	 * 
	 * @return Returns either the last element of the list or null if list is empty.
	 */
	public T getLast() {
		if (start == null)
			throw new NoSuchElementException();
		else
			return getLastFurther(start);
	}

	private T getLastFurther(Node<T> currNode) {
		if (currNode.getNode() == null)
			return currNode.getValue();
		else
			return getLastFurther(currNode.getNode());
	}

	/**
	 * Counts the length of the list. If no entries, returns 0;
	 * 
	 * @return Length of the list. 0 if no entries.
	 */
	public int getLength() {
		int length = 0;
		Node<T> current = this.start;
		while (current != null) {
			length++;
			current = current.getNode();
		}
		return length;
	}

	/**
	 * This method retrieves the value at the specified position in the list. It
	 * throws a NullPointerException, if the specified position is outside of the
	 * list.
	 * 
	 * @param position The position of the value to retrieve.
	 * @return Returns the value at the specified position in the list.
	 * @throws NullPointerException, if position is outside of the list.
	 */
	public T getValue(int position) {
		if (getLength() - 1 < position || position < 0)
			throw new NullPointerException();

		int currPos = 0;
		T value = getValueFurther(currPos, position, this.start);
		return value;
	}

	private T getValueFurther(int currPos, int position, Node<T> node) {
		if (position == currPos)
			return node.getValue();
		else {
			currPos++;
			return getValueFurther(currPos, position, node.getNode());
		}
	}

	/**
	 * This method inserts the specified value at the specified position in the
	 * list. If the specified position is outside of the length of the list, then it
	 * throws a NullPointerException. If list is empty, no values can be inserted.
	 * Use rather add().
	 * 
	 * @param value    The value to insert into the list.
	 * @param position The position, where to insert the specified value.
	 * @throws NullPointerException, if position is outside of the list.
	 */
	public void insert(T value, int position) {
		if (this.getLength() - 1 < position)
			throw new NullPointerException();
		if (position == 0) {
			Node<T> newNode = new Node<T>(value);
			newNode.setNode(this.start);
			this.start = newNode;
		} else
			insertFurther(value, position, 1, this.start); // Note that currPos is 1 and currNode is at position 0.
	}

	/**
	 * Note: If currPos is 1, then the position of currNod is 0. The currPos is the
	 * desired position. CurrNod is the node before, because it has the needed
	 * reference on the node at the desired position.
	 * 
	 * @param value    The value to insert into the list at the specified position.
	 * @param position The position in the list, where to insert the value.
	 * @param currPos  The next position within the list to check.
	 * @param currNod  The node that was checked last.
	 */
	private void insertFurther(T value, int position, int currPos, Node<T> currNod) {
		if (position == currPos) {
			Node<T> newNode = new Node<T>(value);
			newNode.setNode(currNod.getNode());
			currNod.setNode(newNode);
		} else
			insertFurther(value, position, currPos++, currNod.getNode());
	}

	@Override
	public MyIterator<T> iterator() {		
		return new MyIterator<T>(this);
	}
	
	public void remove(T value) {
		if (start != null) {

			if (this.start.getValue().equals(value))
				this.start = start.getNode();
			else
				removeFurther(value, this.start);
		}
	}

	private void removeFurther(T value, Node<T> current) {
		if (current.getNode() != null) {
			if (current.getNode().getValue().equals(value)) // If next node has the specified value.
				current.setNode(current.getNode().getNode()); // Set Ref Node of current Node to the one after the next
																// node
			else
				removeFurther(value, current.getNode());
		}
	}

	public void replace(T oldValue, T newValue) {
		if (start == null)
			throw new NullPointerException("List is empty yet.");
		else
			replaceFurther(oldValue, newValue, start);
	}

	private void replaceFurther(T oldValue, T newValue, Node<T> current) {
		if (current == null)
			throw new NullPointerException("List is empty yet.");
		else if (!current.getValue().equals(oldValue))
			replaceFurther(oldValue, newValue, current.getNode());
		else
			current.setValue(newValue);
	}
}
