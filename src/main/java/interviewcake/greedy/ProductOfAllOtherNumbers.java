package interviewcake.greedy;

import java.util.Arrays;

/**
 * You have an array of integers, and for each index you want to find the product of every integer
 * except the integer at that index.
 * <p>
 * Write a method getProductsOfAllIntsExceptAtIndex() that takes an array of integers and returns an
 * array of the products.
 * <p>
 * For example, given:
 * <p>
 * [1, 7, 3, 4]
 * <p>
 * your method would return:
 * <p>
 * [84, 12, 28, 21]
 * <p>
 * by calculating:
 * <p>
 * [7 * 3 * 4,  1 * 3 * 4,  1 * 7 * 4,  1 * 7 * 3]
 * <p>
 * Here's the catch: You can't use division in your solution!
 */
public class ProductOfAllOtherNumbers {

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 7, 3, 4};
        System.out.println(Arrays.toString(productOfAllOtherNumbers(numbers)));

        numbers = new int[]{5, 7, 0, 4};
        System.out.println(Arrays.toString(productOfAllOtherNumbers(numbers)));
    }

    static int[] productOfAllOtherNumbers(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return new int[]{};
        }

        int[] result = new int[numbers.length];

        // first, go through input array from left to right, calculate cumulative
        // product and store it into result array
        result[0] = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            int prod = numbers[i] * result[i - 1];
            result[i] = prod;
        }

        // then, go through input array from right to left, calculate "backward" cumulative product and
        // use it in conjunction with the "forward" cumulative product stored in array result to compute
        // the final products
        int prod = 1; // set to 1 initially so that cumulative product will be the same as last number
        for (int i = numbers.length - 1; i > 0; i--) {
            result[i] = result[i - 1] * prod; // calculate final product
            prod = prod * numbers[i]; // update cumulative product
        }
        result[0] = prod; // don't forget to set first product!

        return result;
    }
}
