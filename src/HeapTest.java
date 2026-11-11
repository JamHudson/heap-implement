import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class HeapTest {
    @Test 
    public void addRoot() {
        Heap heap = new Heap();
        heap.add(25);

        assertEquals(1, heap.size());
        // assertEquals(List.of(25), heap);
    }

    @Test 
    public void addMany() {
        Heap heap = new Heap();
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(5);
        heap.add(6);

        assertEquals(6, heap.size());
        // assertEquals(List.of(1,2,3,4,5,6), heap);
    }

    @Test 
    public void addNone() {
        Heap heap = new Heap();

        assertEquals(0, heap.size());
        // assertEquals(List.of(), heap);
    }
    
    @Test 
    public void popReturnsValue() {
        Heap heap = new Heap();
        heap.add(0);

        assertEquals(0, heap.pop());
    }

    @Test 
    public void popRemovesValue() {
        Heap heap = new Heap();
        heap.add(1);
        heap.pop();

        assertEquals(0, heap.size());
    }

    @Test
    public void popIsAlwaysSmallest() {
        Heap heap = new Heap();
        heap.add(2);
        heap.add(1);
        heap.add(0);

        List<Integer> pulled = new ArrayList<>();
        pulled.add(heap.pop());
        pulled.add(heap.pop());
        pulled.add(heap.pop());

        assertEquals(List.of(0,1,2), pulled);
    }

    @Test 
    public void peekReturnsValue() {
        Heap heap = new Heap();
        heap.add(5);

        assertEquals(5, heap.peek());
    }

    @Test
    public void peekDoesNotRemoveValue() {
        Heap heap = new Heap();
        heap.add(6);

        assertEquals(1, heap.size());
    }

    @Test
    public void peekIsAlwaysSmallest() {
        Heap heap = new Heap();
        heap.add(8);
        heap.add(5);
        heap.add(7);
        heap.add(4); // Smallest
        heap.add(13);

        assertEquals(4, heap.peek());
    }

    @Test
    public void peekANDpopMatch() {
        Heap heap = new Heap();
        heap.add(5);
        heap.add(2); // Smallest
        heap.add(8);
        heap.add(13);
        heap.add(27);

        assertTrue(heap.peek() == heap.pop());
    }

}
