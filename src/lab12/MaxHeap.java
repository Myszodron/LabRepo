package lab12;

/**
 * MaxHeap represents a binary max heap implemented using an array
 *
 * In a max heap, each parent node has a value greater than or equal
 * to its children. The largest element is therefore always stored
 * at the root (index 0)
 *
 * Class encapsulates heap construction and heap operations
 * Contains no input/output logic
 */
public class MaxHeap {

    private final int[] heapData;
    private int heapSize;

    /**
     * Creates a MaxHeap from the given array
     * The heap is built form bottom-up
     *
     * @param array array to be transformed into a heap
     */
    public MaxHeap(int[] array) {
        this.heapData = array;
        this.heapSize = array.length;
        buildHeap();
    }

    /**
     * Builds the heap by restoring the heap property
     * starting from the last non-leaf node
     */
    private void buildHeap() {
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    /**
     * Removes and returns the maximum element from the heap
     *
     * @return maximum value in the heap
     * @throws IllegalStateException if the heap is empty
     */
    public int extractMax() {
        if (heapSize == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int max = heapData[0];
        heapData[0] = heapData[heapSize - 1];
        heapSize--;

        heapifyDown(0);
        return max;
    }

    /**
     * Restores the heap property by moving an element downward
     *
     * @param index starting position
     */
    private void heapifyDown(int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heapSize && heapData[left] > heapData[largest]) {
            largest = left;
        }
        if (right < heapSize && heapData[right] > heapData[largest]) {
            largest = right;
        }

        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    private void swap(int i, int j) {
        int temp = heapData[i];
        heapData[i] = heapData[j];
        heapData[j] = temp;
    }
}
