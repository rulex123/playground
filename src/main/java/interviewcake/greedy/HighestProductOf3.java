package interviewcake.greedy;

/**
 * Given an array of integers, find the highest product you can get from three of the integers.
 * <p>
 * The input arrayOfInts will always have at least three integers.
 */
public class HighestProductOf3 {

    public static void main(String[] args) {
        int[] numbers = new int[]{10, 7, 5, 11, 4, 8};
        System.out.println(highestProductOf3(numbers));

        numbers = new int[]{10, -9, -10, 11, 4, 8};
        System.out.println(highestProductOf3(numbers));

        numbers = new int[]{-10, -8, -6, -4, -2, -1};
        System.out.println(highestProductOf3(numbers));

        numbers = new int[]{-10, -10, 3, 2, 1};
        System.out.println(highestProductOf3(numbers));
    }

    static int highestProductOf3(int[] numbers) {
        // assuming input array has at least 3 numbers
        int biggestNumbersCount = 0;
        Integer[] biggestNumbers = new Integer[3]; // the 3 biggest numbers (positive or negative)
        int negNumbersCount = 0;
        Integer[] biggestNegNumbers = new Integer[2]; // the 2 biggest negative numbers

        for (int i = 0; i < numbers.length; i++) {
            int currNumber = numbers[i];
            findBiggestNumbers(biggestNumbers, currNumber, biggestNumbersCount);
            biggestNumbersCount++;
            if (currNumber < 0) {
                findBiggestNegNumbers(biggestNegNumbers, currNumber, negNumbersCount);
                negNumbersCount++;
            }
        }

        // now calculate the biggest possible product
        int product = biggestNumbers[0] * biggestNumbers[1] * biggestNumbers[2];
        // check if we have at least 2 negative numbers: if we do, then multiply them to create a
        // positive number, and then multiply that by the biggest number in the input array. Compare
        // this alternative product with the one we previously calculated.
        if (negNumbersCount > 1) {
            int altProduct =
                    maxFrom3Numbers(biggestNumbers) * biggestNegNumbers[0] * biggestNegNumbers[1];
            if (altProduct > product) {
                return altProduct;
            }
        }
        return product;
    }

    private static int maxFrom3Numbers(final Integer[] numbers) {
        int max = Math.max(numbers[0], numbers[1]);
        max = Math.max(max, numbers[2]);
        return max;
    }

    private static void findBiggestNegNumbers(Integer[] biggestNegNumbers, int number, int count) {
        if (count < 2) { // have we already seen 2 negative numbers?
            biggestNegNumbers[count] = number;
            return;
        }

        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < biggestNegNumbers.length; i++) {
            if (biggestNegNumbers[i] > max) { // look for current max and its index in array
                max = biggestNegNumbers[i];
                maxIndex = i;
            }
        }

        if (number < max) { // replace current max if necessary
            biggestNegNumbers[maxIndex] = number;
        }
    }

    private static void findBiggestNumbers(Integer[] biggestNumbers, int number, int count) {
        if (count < 3) { // have we already seen 3 numbers?
            biggestNumbers[count] = number;
            return;
        }

        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < biggestNumbers.length; i++) {
            if (biggestNumbers[i] < min) { // look for current min and its index in array
                min = biggestNumbers[i];
                minIndex = i;
            }
        }

        if (number > min) { // replace current min if necessary
            biggestNumbers[minIndex] = number;
        }
    }
}
