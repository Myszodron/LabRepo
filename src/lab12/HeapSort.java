package lab12;

/**
 * Encapsulates the heap sort algorithm
 *
 * Class owns the array it sorts and uses a MaxHeap
 * to perform the sorting operation
 *
 * Heap Sort has a time complexity of O(n log n) in all cases
 * and is not a stable sorting algorithm
 */
public class HeapSort {

    private final int[] data;

    /**
     * Creates a HeapSort object for the given array
     *
     * @param array array to be sorted
     */
    public HeapSort(int[] array) {
        this.data = array;
    }

    /**
     * Sorts the internal array in ascending order
     */
    public void sort() {
        MaxHeap heap = new MaxHeap(data);

        for (int i = data.length - 1; i >= 0; i--) {
            data[i] = heap.extractMax();
        }
    }

    /**
     * Checks whether the array is sorted correctly
     *
     * @return true if sorted, false otherwise
     */
    public boolean isSorted() {
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] > data[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the sorted array
     *
     * @return sorted array
     */
    public int[] getArray() {
        return data;
    }
}
