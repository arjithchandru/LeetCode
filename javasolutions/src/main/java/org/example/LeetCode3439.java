//LeetCode 3439: Reschedule Meetings for Maximum Free Time I
package org.example;

import java.util.Scanner;

public class LeetCode3439 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Constraints:  1 <= k <= events.length, 1 <= k * events.length <= 106, 1 <= startDayi <= endDayi <= 1091 <= valuei <= 106");
        System.out.println("Enter the eventTime: ");
        int eventTime = sc.nextInt();
        System.out.println("Enter the total amount of Start and End Time: ");
        int totalTime = sc.nextInt();
        int[] startTime = new int[totalTime];
        int[] endTime = new int[totalTime];
        for (int i = 0; i < totalTime; i++) {
            System.out.printf("Enter start time for event %d: ", i + 1);
            startTime[i] = sc.nextInt();
            System.out.printf("Enter end time for event %d: ", i + 1);
            endTime[i] = sc.nextInt();
        }
        System.out.println("Enter the maximum number of events that can be shifted: ");
        int maxShift = sc.nextInt();
        sc.close();

        int maxFreeTime = maxFreeTime(eventTime, maxShift, startTime, endTime);
        System.out.println("Maximum free time after shifting events: " + maxFreeTime);
    }

    /**
     * Calculates the maximum free time after shifting up to maxShift events.
     * Uses a sliding window to find the largest sum of consecutive gaps between meetings.
     *
     * @param eventTime The total time span for all events
     * @param maxShift  The maximum number of events that can be shifted
     * @param startTime Array of event start times (sorted, non-overlapping)
     * @param endTime   Array of event end times (sorted, non-overlapping)
     * @return The maximum free time achievable
     */
    private static int maxFreeTime(int eventTime, int maxShift, int[] startTime, int[] endTime) {
        int n = startTime.length;
        // Calculate the gaps between meetings
        int[] gaps = new int[n + 1];
        gaps[0] = startTime[0];
        for (int i = 1; i < n; i++) {
            gaps[i] = startTime[i] - endTime[i - 1];
        }
        gaps[n] = eventTime - endTime[n - 1];

        // Use a sliding window to find the maximum sum of (maxShift + 1) consecutive gaps
        int windowSize = maxShift + 1;
        int currentSum = 0;
        for (int i = 0; i < windowSize && i < gaps.length; i++) {
            currentSum += gaps[i];
        }
        int maxFreeTime = currentSum;

        for (int i = windowSize; i < gaps.length; i++) {
            currentSum += gaps[i] - gaps[i - windowSize];
            maxFreeTime = Math.max(maxFreeTime, currentSum);
        }
        return maxFreeTime;
    }
}
