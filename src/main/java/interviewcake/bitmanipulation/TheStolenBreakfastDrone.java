package interviewcake.bitmanipulation;

/**
 * Your company delivers breakfast via autonomous quadcopter drones. And something mysterious has
 * happened.
 * <p>
 * Each breakfast delivery is assigned a unique ID, a positive integer. When one of the company's
 * 100 drones takes off with a delivery, the delivery's ID is added to an array,
 * deliveryIdConfirmations. When the drone comes back and lands, the ID is again added to the same
 * array.
 * <p>
 * After breakfast this morning there were only 99 drones on the tarmac. One of the drones never
 * made it back from a delivery. We suspect a secret agent from Amazon placed an order and stole one
 * of our patented drones. To track them down, we need to find their delivery ID.
 * <p>
 * Given the array of IDs, which contains many duplicate integers and one unique integer, find the
 * unique integer.
 * <p>
 * The IDs are not guaranteed to be sorted or sequential. Orders aren't always fulfilled in the
 * order they were received, and some deliveries get cancelled before takeoff.
 */
public class TheStolenBreakfastDrone {

    public static void main(String[] args) {
        int[] deliveryIdConfirmations = new int[]{33, 24, 2, 11, 17, 41, 22, 5, 33, 2, 22, 5, 11, 17
                , 24}; // 41 never made it back :(
        System.out.println(theStolenBreakfastDrone(deliveryIdConfirmations));
    }

    static int theStolenBreakfastDrone(int[] deliveryIds) {
        if (deliveryIds == null || deliveryIds.length == 0) {
            return -1;
        }

        int result = 0;
        for (int i = 0; i < deliveryIds.length; i++) {
            result = result ^ deliveryIds[i];
        }

        return result;
    }
}
