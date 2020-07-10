package lists;

import java.util.NoSuchElementException;

public class MyIterator<T> implements java.util.Iterator<T>{

	private LinkedList<T> list;
	
	public MyIterator(LinkedList<T> list) {
		this.list = list;
	}
	
	@Override
	public boolean hasNext() {
		if (list.getNext() == null) { // => next is before start
			if (list.getStart() == null)
				return false;
		} else if (list.getNext().getNode() == null)
			return false;

		return true;
	}

	@Override
	public T next() {
		if (list.getNext() == null)
			if (list.getStart() == null)
				throw new NoSuchElementException();
			else
				list.setNext(list.getStart());
		else if (list.getNext().getNode() == null)
			throw new NoSuchElementException();
		else
			list.setNext(list.getNext().getNode());

		return list.getNext().getValue();
	}	
}
