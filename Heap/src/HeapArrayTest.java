import static org.junit.Assert.*;
import java.util.Comparator;
import org.junit.Test;

public class HeapArrayTest<E> {
	
	
	HeapArray<Integer> heap = new HeapArray<>(3, Comparator.comparingInt(n1 -> n2));

	@Test
	public void test() {
		assertTrue(heap.isEmpty()==true);
		assertTrue(heap.insertElement(12)==true);
		assertTrue(heap.insertElement(7)==true);
		assertTrue(heap.isEmpty()==false);
		assertTrue(heap.insertElement(8)==true);
		assertTrue(heap.insertElement(5)==true);
		assertTrue(heap.isEmpty()==false);
		assertTrue(heap.element()==8);
	}

}
