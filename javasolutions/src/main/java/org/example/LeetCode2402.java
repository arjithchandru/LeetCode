//LeetCode 2402: Meeting Rooms III
package org.example;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LeetCode2402 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of rooms available: ");
        int rooms = sc.nextInt();
        System.out.println("Enter number of meetings: ");
        int n = sc.nextInt();
        int[][] meetings = new int[n][2];
        for (int i = 0; i < n; i++) {
            System.out.printf("Enter start time for meeting %d: ", i + 1);
            meetings[i][0] = sc.nextInt();
            System.out.printf("Enter end time for meeting %d: ", i + 1);
            meetings[i][1] = sc.nextInt();
        }
        sc.close();
        int mostBooked = mostBooked(rooms, meetings);
        System.out.println("The most booked room is: " + mostBooked);
    }

    private static int mostBooked(int rooms, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int[] count = new int[rooms]; // Count of bookings for each room

        //0. room 1. end time
        PriorityQueue<int[]> occupiedRooms = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0]; // If end times are equal, sort by room number
            } else {
                return a[1] - b[1]; // Sort by end time
            }
        });
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();

        for (int i = 0; i < rooms; i++) {
            availableRooms.offer(i); // Add all rooms to the available queue
        }

        for (int[] meeting : meetings) {
            int startTime = meeting[0];
            int endTime = meeting[1];
            int totalTime = endTime - startTime;

            while (!occupiedRooms.isEmpty() && occupiedRooms.peek()[1] <= startTime) {
                int[] room = occupiedRooms.poll();
                availableRooms.offer(room[0]); // Add the room back to available rooms
            }

            if (!availableRooms.isEmpty()) {
                int room = availableRooms.poll(); // Get the next available room
                count[room]++; // Increment the booking count for this room
                occupiedRooms.offer(new int[]{room, endTime}); // Add the room to occupied rooms with its end time
            } else {
                // If no rooms are available find the earliest available room
                int[] room = occupiedRooms.poll();// Get the room with the earliest end time
                count[room[0]]++; // Increment the booking count for this room
                occupiedRooms.offer(new int[]{room[0], room[1] + totalTime}); // Update the end time of this room
            }
        }
        // Find the most booked room
        int maxBookings = 0;
        int mostBookedRoom = 0;
        for (int i = 0; i < rooms; i++) {
            if (count[i] > maxBookings) {
                maxBookings = count[i];
                mostBookedRoom = i;
            }
        }

        return mostBookedRoom;
    }
}
