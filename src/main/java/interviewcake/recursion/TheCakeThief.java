package interviewcake.recursion;

/**
 * You are a renowned thief who has recently switched from stealing precious metals to stealing
 * cakes because of the insane profit margins. You end up hitting the jackpot, breaking into the
 * world's largest privately owned stock of cakes - the vault of the Queen of England.
 *
 * While Queen Elizabeth has a limited number of types of cake, she has an unlimited supply of each
 * type.
 *
 * Each type of cake has a weight and a value, stored in objects of a <code>CakeType</code> class.
 * You brought a duffel bag that can hold limited weight, and you want to make off with the most
 * valuable haul possible.
 *
 * Write a method maxDuffelBagValue() that takes an array of cake type objects and a weight
 * capacity, and returns the maximum monetary value the duffel bag can hold.
 */
public class TheCakeThief {

  public static void main(String[] args) {
    CakeType[] cakeTypes = new CakeType[]{
        new CakeType(7, 160),
        new CakeType(3, 90),
        new CakeType(2, 15),
        };

    System.out.println(maxDuffelBagValue(cakeTypes, 20));
  }

  static int maxDuffelBagValue(CakeType[] cakeTypes, int capacity) {
    if (cakeTypes == null || cakeTypes.length == 0) {
      throw new IllegalArgumentException("must have at least one cake type");
    }
    if (capacity < 0) {
      throw new IllegalArgumentException("invalid capacity");
    }

    int[] maxProfit = new int[capacity + 1];
    for (int i = 0; i < cakeTypes.length; i++) {
      CakeType currCakeType = cakeTypes[i];
      for (int j = 1; j < maxProfit.length; j++) {
        int currCapacity = j;
        if (currCakeType.weight > currCapacity) {
          continue; // skip if current cake's weight exceeds capacity
        }
        // if a cake weighs 0 and has non-zero value then the profit of our duffel bag is infinite
        if (currCakeType.weight == 0 && currCakeType.value != 0) {
          throw new RuntimeException("infinity and beyond!");
        }

        // what is max profit if we don't use current cake type?
        int maxProfitWithout = maxProfit[currCapacity];
        // what is max profit if we use current cake type?
        int capacityRemainder = currCapacity - currCakeType.weight;
        int maxProfitWith = currCakeType.value + maxProfit[capacityRemainder];
        // what is max possible profit overall?
        maxProfit[currCapacity] = Math.max(maxProfitWithout, maxProfitWith);
      }
    }

    return maxProfit[maxProfit.length - 1];
  }

  static class CakeType {

    final int weight;
    final int value;

    public CakeType(int weight, int value) {
      this.weight = weight;
      this.value = value;
    }
  }
}
