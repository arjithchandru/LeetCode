//LeetCode 1751: Maximum Number of Events That Can Be Attended II
package org.example;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class LeetCode1751 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter total number of events: ");
        int totalEvents = sc.nextInt();
        int[][] events = new int[totalEvents][3];
        for (int i = 0; i < totalEvents; i++) {
            System.out.printf("\nEnter start time for event %d: ", i + 1);
            events[i][0] = sc.nextInt(); // start time
            System.out.printf("\nEnter end time for event %d: ", i + 1);
            events[i][1] = sc.nextInt(); // end time
            System.out.printf("\nEnter value for event %d: ", i + 1);
            events[i][2] = sc.nextInt(); // value
        }

        System.out.println("\nEnter maximum number of events that can be attended: ");
        int k = sc.nextInt();
        sc.close();
        int maxValue = maxValue(events, k);
        System.out.println("Maximum value of events that can be attended: " + maxValue);
    }

    /**
     * Calculates the maximum value of events that can be attended, given a limit on the number of events.
     * Uses dynamic programming with memoization and binary search for efficiency.
     *
     * @param events 2D array where each event is represented as [start, end, value]
     * @param k      Maximum number of events that can be attended
     * @return Maximum total value achievable by attending up to k non-overlapping events
     */
    public static int maxValue(@Nonnull int[][] events, int k) {
        // Sort events by start time
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));

        int length = events.length;
        Integer[][] memory = new Integer[length][k + 1];
        return dfs(0, k, events, memory);
    }

    /**
     * Recursive helper function with memoization to explore event attendance combinations.
     *
     * @param index         Current event index
     * @param balanceEvents Remaining number of events that can be attended
     * @param events        Sorted list of events
     * @param memory        Memoization table
     * @return Maximum value achievable from the current state
     */
    private static int dfs(int index, int balanceEvents, @Nonnull int[][] events, @Nonnull Integer[][] memory) {
        if (index == events.length || balanceEvents == 0) {
            return 0;
        }

        if (memory[index][balanceEvents] != null) {
            return memory[index][balanceEvents];
        }

        int toSkip = dfs(index + 1, balanceEvents, events, memory);
        int nextIndex = findNextEvent(events, events[index][1]);
        int toTake = events[index][2] + dfs(nextIndex, balanceEvents - 1, events, memory);

        memory[index][balanceEvents] = Math.max(toSkip, toTake);
        return memory[index][balanceEvents];
    }


    /**
     * Binary search to find the index of the next event that starts after the given end time.
     *
     * @param events  Sorted list of events
     * @param endTime End time of the current event
     * @return Index of the next non-overlapping event
     */
    private static int findNextEvent(@Nonnull int[][] events, int endTime) {
        int left = 0, right = events.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (events[mid][0] > endTime) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left; // Return the index of the next event that starts after endTime
    }
}
