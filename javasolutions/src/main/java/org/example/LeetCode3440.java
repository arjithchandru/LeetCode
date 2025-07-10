//LeetCode 3440: Reschedule Meetings for Maximum Free Time II
package org.example;

import java.util.Scanner;

/**
 * Constraints:
 * 1 <= eventTime <= 10^9
 * n == startTime.length == endTime.length
 * 2 <= n <= 10^5
 * 0 <= startTime[i] < endTime[i] <= eventTime
 * endTime[i] <= startTime[i + 1] where i lies in the range [0, n - 2].
 */
public class LeetCode3440 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of events: ");
        int eventTime = sc.nextInt();
        System.out.println("Enter the total number of meetings: ");
        int n = sc.nextInt();
        int[] startTime = new int[n];
        int[] endTime = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("Enter start time for event %d: ", i + 1);
            startTime[i] = sc.nextInt();
            System.out.printf("Enter end time for event %d: ", i + 1);
            endTime[i] = sc.nextInt();
        }
        sc.close();
        int maxFreeTime = maxFreeTime(eventTime, startTime, endTime);
        System.out.println("Maximum free time after shifting events: " + maxFreeTime);
    }

    /**
     * Calculates the maximum free time after rescheduling meetings.
     *
     * @param eventTime The total time of the event.
     * @param startTime Array of meeting start times.
     * @param endTime   Array of meeting end times.
     * @return The maximum free time after shifting events.
     */
    private static int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        // Calculate the gaps between meetings
        int[] gaps = new int[n + 1];
        gaps[0] = startTime[0];
        for (int i = 1; i < n; i++) {
            gaps[i] = startTime[i] - endTime[i - 1];
        }
        gaps[n] = eventTime - endTime[n - 1];
        int[] leftMax = new int[n + 1];
        int[] rightMax = new int[n + 1];

        leftMax[0] = gaps[0];
        for (int i = 1; i <= n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], gaps[i]);
        }

        rightMax[n] = gaps[n];
        for (int i = n - 1; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], gaps[i]);
        }

        int maxFreeTime = 0;
        for (int i = 0; i < n; i++) {
            int meetingOccupiedTime = endTime[i] - startTime[i];
            int freeTime = gaps[i] + gaps[i + 1];
            if ((i > 0 && leftMax[i - 1] >= meetingOccupiedTime) || (i + 2 < n && rightMax[i + 1] >= meetingOccupiedTime)) {
                freeTime += meetingOccupiedTime;
            }
            maxFreeTime = Math.max(maxFreeTime, freeTime);
        }

        return maxFreeTime;
    }
}
