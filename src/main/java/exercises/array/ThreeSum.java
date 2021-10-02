package exercises.array;

import java.util.*;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all
 * unique triplets in
 * the array which gives the sum of zero. Note: The solution set must not contain duplicate
 * triplets.
 *
 * @author emanno
 * @version 1.0 Sep 16, 2017
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = new int[]{
                -1, 0, 1, 2, -1, -4
        };
        Set<List<Integer>> triplets = threeSum_sorting(nums);

        for (List<Integer> triplet : triplets) {
            System.out.println(triplet);
        }
    }

    public static Set<List<Integer>> threeSum_sorting(int[] nums) {
        Set<List<Integer>> triplets = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            for (int k = i + 1, j = nums.length - 1; k < j; ) {
                int sum = nums[i] + nums[k] + nums[j];
                if (sum == 0) {
                    Integer[] triplet = new Integer[]{
                            nums[i], nums[k], nums[j]
                    };
                    Arrays.sort(triplet);
                    triplets.add(Arrays.asList(triplet));
                    k++;
                    j--;
                } else if (sum < 0) {
                    k++;
                } else {
                    j--;
                }
            }
        }

        return triplets;
    }


    private static Map<Integer, List<Integer>> buildNumToIndexMap(int[] nums) {
        Map<Integer, List<Integer>> numToIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numToIndexMap.containsKey(nums[i])) {
                List<Integer> newList = new ArrayList<>(numToIndexMap.get(nums[i]));
                newList.add(i);
                numToIndexMap.put(nums[i], newList);
            } else {
                numToIndexMap.put(nums[i], Arrays.asList(i));
            }
        }
        return numToIndexMap;
    }


    public static Set<List<Integer>> threeSum_hashMap(int nums[]) {
        Set<List<Integer>> triplets = new HashSet<>();
        Map<Integer, List<Integer>> numToIndexMap = buildNumToIndexMap(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int complement = -(nums[i] + nums[j]);
                if (numToIndexMap.containsKey(complement)) {
                    if ((complement == nums[i] || complement == nums[j])
                            && numToIndexMap.get(complement).size() == 1) {
                        // do not reuse nums[i] and nums[j] in triplet
                        continue;
                    }

                    Integer[] triplet = new Integer[]{
                            nums[i], nums[j], complement
                    };
                    Arrays.sort(triplet); // sort the triplet in order to avoid duplicates
                    triplets.add(Arrays.asList(triplet));
                }
            }
        }

        return triplets;
    }


    /**
     * This brute force solution has runtime complexity of O(n^3)
     */
    public static Set<List<Integer>> threeSum_bruteForce(int[] nums) {
        Set<List<Integer>> triplets = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        Integer[] triplet = new Integer[]{
                                nums[i], nums[j], nums[k]
                        };
                        Arrays.sort(triplet); // sort the triplet in order to avoid duplicates
                        triplets.add(Arrays.asList(triplet));
                    }
                }
            }
        }
        return triplets;
    }

}
