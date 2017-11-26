package heap;

import java.util.NoSuchElementException;

public interface Heap<E> extends Iterable<E> 
{ 
	/** Add specified element into this Heap, if there is enough space
	 * or if smaller than the current highest element
	 * 
	 * @return true if the element was successfully added
	 */
	boolean insertElement(E e); 
	
    /**
     * Retrieves (without removing) the highest element of this heap
     *
     * @return the highest element of this heap
     * @throws NoSuchElementException if this heap is empty
     */
    E element();
    
   
    /** Returns true if this heap contains no elements. */
    boolean isEmpty();

    
    /** Returns the number of elements contained in this heap */
    int size();
    
}
