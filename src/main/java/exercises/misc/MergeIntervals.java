package exercises.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals. <br>
 * For example, given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 *
 * @author emanno
 * @version 1.0 Sep 24, 2017
 */
public class MergeIntervals {

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(4, 7),
                new Interval(8, 10), new Interval(15, 18));

        System.out.println(mergeIntervals(intervals));

    }

    public static List<Interval> mergeIntervals(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty())
            return intervals;

        // sort intervals by start time
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

        List<Interval> mergedIntervals = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (curr.start <= end) {
                end = Math.max(curr.end, end);
            } else {
                mergedIntervals.add(new Interval(start, end));
                start = curr.start;
                end = curr.end;
            }
        }

        mergedIntervals.add(new Interval(start, end));

        return mergedIntervals;
    }

    static class Interval {
        int start;
        int end;


        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        public String toString() {
            return "Interval [start=" + this.start + ", end=" + this.end + "]";
        }

    }

}
