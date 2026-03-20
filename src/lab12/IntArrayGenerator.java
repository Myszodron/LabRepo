package lab12;

import java.util.Random;

/**
 * IntArrayGenerator creates arrays filled with random integers
 *
 * Class separates data generation from sorting logic
 * and improves overall program organization
 */
public class IntArrayGenerator {

    private final Random rng;

    /**
     * Creates a generator with a default random seed
     */
    public IntArrayGenerator() {
        this.rng = new Random();
    }

    /**
     * Generates an array of random integers
     *
     * @param length number of elements
     * @return generated array
     * @throws IllegalArgumentException if length is negative
     */
    public int[] generateArray(int length) {
        if (length < 0) {
            throw new IllegalArgumentException(
                    "Array length has to be positive");
        }

        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = rng.nextInt(length + 1);
        }

        return result;
    }
}
