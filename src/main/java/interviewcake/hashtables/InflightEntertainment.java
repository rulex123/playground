package interviewcake.hashtables;

import java.util.HashSet;
import java.util.Set;

/**
 * You've built an inflight entertainment system with on-demand movie streaming.
 * <p>
 * Users on longer flights like to start a second movie right when their first one ends, but they
 * complain that the plane usually lands before they can see the ending. So you're building a
 * feature for choosing two movies whose total runtimes will equal the exact flight length.
 * <p>
 * Write a method that takes an integer flightLength (in minutes) and an array of integers
 * movieLengths (in minutes) and returns a boolean indicating whether there are two numbers in
 * movieLengths whose sum equals flightLength.
 * <p>
 * When building your method:
 * <p>
 * - Assume your users will watch exactly two movies
 * - Don't make your users watch the same movie twice
 * - Optimize for runtime over memory
 */
public class InflightEntertainment {

    public static void main(String[] args) {
        System.out.println(inflightEntertainment(60, new int[]{10, 20, 30}));
        System.out.println(inflightEntertainment(60, new int[]{10, 20, 40}));
        System.out.println(inflightEntertainment(60, new int[]{50, 20, 10}));
    }

    static boolean inflightEntertainment(int flightLength, int[] movieLengths) {
        if (flightLength < 0 || (movieLengths == null || movieLengths.length == 0)) {
            throw new IllegalArgumentException();
        }

        Set<Integer> lengths = new HashSet<>();
        for (int i = 0; i < movieLengths.length; i++) {
            int currMovieLength = movieLengths[i];

            // check if set contains a movie that complements curr movie
            int complement = flightLength - currMovieLength;
            if (complement > 0 && lengths.contains(complement)) {
                return true;
            }
            lengths.add(currMovieLength);
        }

        return false;
    }
}
