package exercises.misc;

import java.util.Arrays;

/**
 * Implement a function meetingPlanner that given the availability, slotsA and slotsB, of two people and a meeting
 * duration dur, returns the earliest time slot that works for both of them and is of duration dur. If there is no
 * common time slot that satisfies the duration requirement, return null. Time is given in a Unix format called Epoch,
 * which is a nonnegative integer holding the number of seconds that have elapsed since 00:00:00 UTC, Thursday, 1
 * January 1970. Each person's availability is represented by an array of pairs. Each pair is an epoch array of size
 * two. The first epoch in a pair represents the start time of a slot. The second epoch is the end time of that slot.
 * The input variable dur is a positive integer that represents the duration of a meeting in seconds. The output is also
 * a pair represented by an epoch array of size two. In your implementation assume that the time slots in a person's
 * availability are disjointed, i.e, time slots in a person's availability don't overlap. Further assume that the slots
 * are sorted by slots' start time. Implement an efficient solution and analyze its time and space complexities.
 *
 * @author emanno
 * @version 1.0 Aug 5, 2017
 */
public class MeetingPlanner {

    public static void main(String[] args) {

        int[][] slotsA = new int[][]{
                new int[]{
                        10, 50
                }, new int[]{
                60, 120
        }, new int[]{
                140, 210
        }
        };

        int[][] slotsB = new int[][]{
                new int[]{
                        0, 15
                }, new int[]{
                60, 70
        }
        };

        System.out.println(Arrays.toString(meetingPlanner(slotsA, slotsB, 8)));
        System.out.println(Arrays.toString(meetingPlanner(slotsA, slotsB, 12)));
    }


    public static int[] meetingPlanner(int[][] slotsA, int[][] slotsB, int dur) {
        int indexSlotA = 0;
        int indexSlotB = 0;

        while (indexSlotA < slotsA.length && indexSlotB < slotsB.length) {
            int start = Math.max(slotsA[indexSlotA][0], slotsB[indexSlotB][0]);
            int end = Math.min(slotsA[indexSlotA][1], slotsB[indexSlotB][1]);

            if (start + dur <= end) {
                return new int[]{
                        start, start + dur
                };
            } else {
                if (slotsB[indexSlotB][1] < slotsA[indexSlotA][1]) {
                    indexSlotB++;
                } else {
                    indexSlotA++;
                }
            }
        }

        return null;
    }

}
