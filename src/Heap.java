import java.util.ArrayList;
import java.util.List;

/**
 * A min-heap. 
 * 
 * Must contain functionality for the following: (n = # of elements in heap)
 * 
 * Adding a new value to the heap  O(log(n))
 * Popping the front of the heap O(log(n))
 * Peeking at the front of the heap without removing the element O(1)
 * Getting the size of the heap O(1)
 * Checking whether the heap is empty O(1)
 * 
 * 
 * The Heap must hold ints
 * 
 * Use List/ArrayList as a backing array, but DO NOT use the 
 * PriorityQueue class. Do not make Node objects, use array
 * indexing instead.
 * 
 * It is up to you to make design decisions about how to:
 *  - name methods
 *  - determine return value and argument types
 *  - hold private instance variables
 */
public class Heap {
    private List<Integer> array;

    public Heap() {
        this.array = new ArrayList<>();
    }

    public void add(int data) {
        array.add(data);
    }

    public int pop() {
        return -1;
    }

    public int peek() {
        return -1;
    }

    public int size() {
        return array.size();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * Returns the parent index of the given.
     * @param idx
     * @return parent index
     */
    private static int parentOf(int idx) {
        return (idx - 1) / 2;
    }

    /**
     * Returns the left child index of the given.
     * @param idx
     * @return left child index
     */
    private static int leftOf(int idx) {
        return (2 * idx) + 1;
    }

    /**
     * Returns the right child index of the given.
     * @param idx
     * @return right child index
     */
    private static int rightOf(int idx) {
        return (2 * idx) + 2;
    }
}