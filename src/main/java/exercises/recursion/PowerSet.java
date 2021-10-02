package exercises.recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * Write a method to return all subsets of a set.
 *
 * @author emanno
 * @version 1.0 May 4, 2017
 */
public class PowerSet {

    public static void main(String[] args) {

        Set<Integer> input = new HashSet<>();
        input.add(1);
        input.add(2);
        input.add(3);

        System.out.println(execute(input));
    }


    private static Set<Set<Integer>> execute(Set<Integer> input) {

        // base case
        if (input.isEmpty()) {
            Set<Set<Integer>> res = new HashSet<>();
            res.add(new HashSet<>());
            return res;
        }

        // popping an element from the set to reuse later
        Integer temp = input.iterator().next();
        input.remove(temp);

        Set<Set<Integer>> res = new HashSet<>(execute(input));

        Set<Set<Integer>> newSets = new HashSet<>();
        for (Set<Integer> set : res) {
            Set<Integer> newSet = new HashSet<>(set);
            set.add(temp);
            newSets.add(newSet);
        }

        res.addAll(newSets);
        return res;
    }

}
