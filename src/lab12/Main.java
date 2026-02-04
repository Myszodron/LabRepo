package lab12;

/**
 * Main class demonstrating Heap Sort
 *
 * The program
 *  - generates random data
 *  - sorts it using heap sort
 *  - prints the results,
 *  - verifies correctness
 *  - states the stability conclusion
 *
 * No user input is required
 */
public class Main {

    private static final int Array_Size = 8;

    public static void main(String[] args) {

        IntArrayGenerator generator = new IntArrayGenerator();
        int[] numbers = generator.generateArray(Array_Size);

        printArray(numbers, "Original array");

        HeapSort sorter = new HeapSort(numbers);
        sorter.sort();

        printArray(sorter.getArray(), "Sorted array");

        System.out.println("Is the array sorted? " +
                (sorter.isSorted() ? "Yes" : "No"));

        System.out.println("\nFinal Thoughts:");
        System.out.println("Heap Sort isn't a stable sorting algorithm");

        checkStability();

    }

    /**
     * Demonstrates that Heap Sort isn't  a stable sorting algorithm
     *
     * The same values are labeled by their original positions
     * A stable sort would preserve the order of equal elements
     * while heap sort does not guarantee this behavior
     */
    private static void checkStability() {

        System.out.println("\nStability Check: ");

        int[] testData = {4, 2, 3, 2, 1, 3, 4};

        System.out.print("Original values: [");
        for (int i = 0; i < testData.length; i++) {
            System.out.print(testData[i]);
            if (i < testData.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        System.out.println("Labeled elements:");
        System.out.println("[4a, 2a, 3a, 2b, 1, 3b, 4b]");

        HeapSort sorter = new HeapSort(testData);
        sorter.sort();

        int[] sorted = sorter.getArray();

        System.out.print("After heap sort: [");
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i]);
            if (i < sorted.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        System.out.println("\nObservation:");
        System.out.println(
                "Elements with equal values (e.g. 2a and 2b, 3a and 3b)\n" +
                        "do not have their original relative order guaranteed\n" +
                        "So Heap Sort isn't stable"
        );
    }

    private static void printArray(int[] array, String label) {
        System.out.print(label + ": [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
