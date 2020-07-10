package hashing;

import java.util.Iterator;
import lists.*;

/**
 * This is a datastructure that stores values by hashing and closed addressing.
 * The value is being hashed using the hashCode() method of the JRE-Library and
 * stored in a dynamic list in an array at the position of its hash. This allows
 * to deal with collisions using closed addressing. Runtime is in Bestcase
 * O(n)=1.
 * 
 * @author Dimitri
 *
 * @param <T>
 */
public class HashList<T> {

	private LinkedList<T>[] adressraum;
	private int size;

	public int getSize() {
		return size;
	}

	public HashList() {
		this.adressraum = (LinkedList<T>[]) new LinkedList[Integer.MAX_VALUE];
		this.size = 0;
	}

	public void add(T value) {
		if (adressraum[value.hashCode()] == null)
			adressraum[value.hashCode()] = new LinkedList<T>();

		adressraum[value.hashCode()].add(value);

		this.size++;
	}

	/**
	 * Removes the specified value from the HashList. If HashList contains more than
	 * one of the specified values, then only one will be removed.
	 * 
	 * @param value The value to remove.
	 */
	public void remove(T value) {
		if (adressraum[value.hashCode()] == null)
			throw new NullPointerException();
		else
			adressraum[value.hashCode()].remove(value);
	}

	public boolean exists(T value) {
		boolean exists = false;
		if (this.adressraum[value.hashCode()] != null)
			if (adressraum[value.hashCode()].contains(value))
				exists = true;

		return exists;
	}

	/**
	 * Counts how many times the specified values exist in the list.
	 * 
	 * @param value The value to check how many times it exists in the HashList.
	 * @return Returns the count of the specified value.
	 */
	public int countOf(T value) {
		int count = 0;

		if (adressraum[value.hashCode()] != null) {
			Iterator<T> it = adressraum[value.hashCode()].iterator();
			while (it.hasNext())
				if (it.next().equals(value))
					count++;
		}
		return count;
	}

	/**
	 * This method clears complete HashList of all entries.
	 */
	public void clear() {
		this.adressraum = (LinkedList<T>[]) new LinkedList[Integer.MAX_VALUE];
	}
}
