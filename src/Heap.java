import java.util.ArrayList;
import java.util.List;

/**
 * A min-heap.
 * 
 * Supports the following:
 * Adding a new value to the heap  O(log(n))
 * Popping the front of the heap O(log(n))
 * Peeking at the front of the heap without removing the element O(1)
 * Getting the size of the heap O(1)
 * Checking whether the heap is empty O(1)
 * 
 * Backed by an ArrayList
 */
public class Heap {
    private List<Integer> array;

    /**
     * Creates a new empty heap.
     */
    public Heap() {
        this.array = new ArrayList<>();
    }

    /**
     * Adds the given number to the heap.
     * 
     * @param data
     */
    public void add(int data) {
        int curIdx = size();
        array.add(data);
        // While parent is greater
        while (compare(curIdx, parentOf(curIdx)) < 0) {
            // Move up
            if (swap(curIdx, parentOf(curIdx))) {
                curIdx = parentOf(curIdx);
            } else
                break;
        }
    }

    /**
     * Removes the top of (smallest number in) the heap.
     * 
     * @return next number in the heap.
     * @throws IllegalStateException
     */
    public int pop() throws IllegalStateException {
        if (isEmpty()) throw new IllegalStateException("Cannot pop an empty heap");
        int popped = array.get(0);
        // If this is the only node
        if (size() == 1) {
            array.remove(0);
            return popped;
        }
        int replacement = array.get(size() - 1);
        int curIdx = 0;

        // Remove the moved indexes
        array.remove(0);
        array.remove(size() - 1);

        // Move the replacement to the top.
        array.add(0, replacement);

        // While the replacement has a child smaller than it (not at rest)
        while (true) {
            // If the left child is smaller
            if (inBounds(leftOf(curIdx)) && compare(leftOf(curIdx), curIdx) < 0) {
                // Swap with the left child
                swap(curIdx, leftOf(curIdx));
            }
            // If the right child is smaller
            if (inBounds(rightOf(curIdx)) && compare(rightOf(curIdx), curIdx) < 0) {
                // Swap with the right child
                swap(curIdx, rightOf(curIdx));
            }
            // If neither child is smaller, then end the loop.
            break;
        }

        return popped;
    }

    /**
     * Returns the top (smallest number in) the heap.
     * Does not remove the top from the heap.
     * @return the next number in the heap.
     * @throws IllegalStateException
     */
    public int peek() throws IllegalStateException {
        if (isEmpty()) throw new IllegalStateException("Cannot peek an empty heap");
        return array.get(0);
    }

    /**
     * Returns the size of the heap.
     * @return the heap size
     */
    public int size() {
        return array.size();
    }

    /**
     * Returns whether the heap is empty.
     * @return True if empty
     */
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * Swaps the values of two indexes.
     * 
     * @param idx1 the first idx.
     * @param idx2 the second idx.
     * @return true if the swap was successful
     */
    private boolean swap(int idx1, int idx2) {
        if (idx1 == idx2)
            return false;
        if (idx1 >= size() || idx2 >= size())
            return false;
        int val1 = array.get(idx1);
        int val2 = array.get(idx2);
        array.set(idx1, val2);
        array.set(idx2, val1);
        return true;
    }

    /**
     * 
     * @param idx1
     * @param idx2
     * @return
     */
    private int compare(int idx1, int idx2) {
        int val1 = array.get(idx1);
        int val2 = array.get(idx2);

        return val1 - val2;
    }

    /**
     * Returns whether the given index is in bounds for this heap.
     * @param idx the index to check for inBounds
     * @return True if inbounds, False otherwise
     */
    private boolean inBounds(int idx) {
        if (idx < 0) return false;
        if (idx >= size()) return false;
        return true;
    }

    /**
     * Returns the parent index of the given.
     * 
     * @param idx
     * @return parent index
     */
    private static int parentOf(int idx) {
        if (idx == 0) return 0;
        return (idx - 1) / 2;
    }

    /**
     * Returns the left child index of the given.
     * 
     * @param idx
     * @return left child index
     */
    private static int leftOf(int idx) {
        return (2 * idx) + 1;
    }

    /**
     * Returns the right child index of the given.
     * 
     * @param idx
     * @return right child index
     */
    private static int rightOf(int idx) {
        return (2 * idx) + 2;
    }
}