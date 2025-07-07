// LeetCode1353: Maximum number of events that can be attended
package org.example;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LeetCode1353 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of the events: ");
        int n = sc.nextInt();
        int[][] events = new int[n][2];
        for (int i = 0; i < n; i++) {
            System.out.printf("Enter the start time for event %d: ", i + 1);
            events[i][0] = sc.nextInt(); // start time
            System.out.printf("Enter the end time for event %d: ", i + 1);
            events[i][1] = sc.nextInt(); // end time
        }
        sc.close();
        int maxEvents = maxEvents(events);
        System.out.println("Maximum number of events that can be attended: " + maxEvents);
    }

    /**
     * Returns the maximum number of events that can be attended.
     * Each event has a start and end day, and only one event can be attended per day.
     * Greedy approach: always attend the event that ends earliest.
     */
    public static int maxEvents(@Nonnull int[][] events) {
        // Sort events by start time
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        // Find the last possible day to attend any event
        int lastDay = 0;
        for (int[] event : events) {
            lastDay = Math.max(lastDay, event[1]);
        }

        // Initialize variables to track the number of events, current day, and count of events attended
        int i = 0, count = 0, day = 0;
        int n = events.length;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        while (day <= lastDay) {
            // Add all events that start on the current day
            while (i < n && events[i][0] == day) {
                minHeap.offer(events[i][1]);
                i++;
            }
            // Remove all the events that have ended before the current day
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }

            //If there are any event available at the current day, attend the one that ends the earliest
            if (!minHeap.isEmpty()) {
                minHeap.poll();
                count++;
            }
            day++;
        }
        return count;
    }
}
