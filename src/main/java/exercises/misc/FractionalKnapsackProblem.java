package exercises.misc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a list of items with values and weights, as well as a max weight, find the maximum value you can generate from
 * items where the sum of the weights is less than the max. You can break items in order to maximise the value.
 *
 * @author emanno
 * @version 1.0 Aug 9, 2017
 */
public class FractionalKnapsackProblem {

    public static void main(String[] args) {
        List<Item> items = Arrays.asList(new Item(10, 60), new Item(20, 100), new Item(30, 120));
        System.out.println(fractionalKnapsack(items, 50)); // expected 240
    }


    public static double fractionalKnapsack(List<Item> items, int availableCapacity) {
        // sort items by valueToWeight ratio
        sort(items);

        // calculate max value
        int maxValue = 0;
        for (Item item : items) {
            double portionOfItem = 0;
            // determine portion of item to put into knapsack
            if (item.weight <= availableCapacity) {
                portionOfItem = 1;
            } else {
                portionOfItem = (availableCapacity / item.weight);
            }
            // put item (whole or portion) into knapsack
            maxValue += portionOfItem * item.value;
            availableCapacity -= portionOfItem * item.weight;

            if (availableCapacity == 0)
                break;
        }

        return maxValue;
    }


    private static void sort(List<Item> items) {
//		Collections.sort( items,
//				(itemA, itemB) -> new Double( itemB.valueToWeightRatio ).compareTo( new Double( itemA.valueToWeightRatio ) ));
        Collections.sort(items,
                (itemA, itemB) -> Double.compare(itemB.valueToWeightRatio, itemA.valueToWeightRatio));
    }

    static class Item {
        final double weight;
        final double value;
        final double valueToWeightRatio;


        public Item(double w, double v) {
            this.weight = w;
            this.value = v;
            this.valueToWeightRatio = this.value / this.weight;
        }
    }

}
