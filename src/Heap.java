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
        // While the new number has not find its final spot
        while (true) {
            boolean currentSmallerThanParent = numAt(curIdx).compareTo(numAt(parentOf(curIdx))) < 0;
            if (currentSmallerThanParent) {
                // Move up
                if (swap(curIdx, parentOf(curIdx)))
                    curIdx = parentOf(curIdx);
                // It can't swap with it (because it doesn't exist)
                else
                    break;
            } else {
                // It's where it needs to be.
                break;
            }
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
        Integer replacement = array.get(size() - 1);
        int curIdx = 0;

        // Replace the first node
        array.set(0, replacement);
        array.remove(size() - 1);

        // While the replacement has not found its final position
        while (true) {
            int idxOfSmallest = getSmallest(curIdx);
            if (idxOfSmallest == -1) break;

            // If the smallest child is smaller than the replacement
            boolean replacementIsGreater = replacement.compareTo(numAt(idxOfSmallest)) > 0;
            if (replacementIsGreater) {
                // Swap with the smallest child
                swap(curIdx, idxOfSmallest);
                curIdx = idxOfSmallest;
            }
            // If the replacement is larger than its children, then end the loop.
            else break;
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
     * Returns the number at the given index.
     * @param idx the index to check.
     * @return the number at the given index.
     */
    private Integer numAt(int idx) throws IndexOutOfBoundsException {
        return array.get(idx);
    }

    /**
     * Returns the index of the smallest child of an index.
     * Returns -1 if there are no children.
     * @param idx the parent index
     * @return smallest child index
     */
    private int getSmallest(int idx) {
        int idxOfSmallest = -1;
        // Check for left child
        int leftIdx = leftOf(idx);
        boolean hasLeft = inBounds(leftIdx);
        // Check for right child
        int rightIdx = rightOf(idx);
        boolean hasRight = inBounds(rightIdx);

        // If both exist
        if (hasLeft && hasRight) {
            // Choose the smaller one
            boolean leftGreater = numAt(leftIdx).compareTo(numAt(rightIdx)) > 0;
            idxOfSmallest = leftGreater ? rightIdx : leftIdx;
        } 
        // Else if only the left one exists
        else if (hasLeft) idxOfSmallest = leftIdx;
        // Else if only the right one exists
        else if (hasRight) idxOfSmallest = rightIdx;
        // Otherwise if there aren't any children.
        return idxOfSmallest;
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