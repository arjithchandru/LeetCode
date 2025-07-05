// LeetCode1394: Find Lucky Integer in an Array
package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.annotation.Nonnull;

public class LeetCode1394 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt user for the length of the array
        System.out.println("Enter the length of an integer array : ");
        int lengthOfArray = sc.nextInt();
        int[] arr = new int[lengthOfArray];

        // Read array elements from user input
        for (int i = 0; i < lengthOfArray; i++) {
            System.out.printf("Enter your %d value to load into array %n ", i + 1);
            arr[i] = sc.nextInt();
        }
        sc.close();

        // Find and print the lucky integer
        int luckInt = getLuckyInteger(arr);
        System.out.println("The lucky integer is : " + luckInt);
    }

    /**
     * Returns the largest lucky integer in the array.
     * A lucky integer is one whose value is equal to its frequency in the array.
     * If no such integer exists, returns -1.
     */
    public static int getLuckyInteger(@Nonnull int[] arr) {
        // Map to store the frequency of each number
        HashMap<Integer, Integer> counter = new HashMap<>();
        int luckyInt = -1;

        // Count the frequency of each number in the array
        for (int num : arr) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        // Check for lucky integers and keep track of the largest one
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getKey().equals(entry.getValue()) && entry.getKey() > luckyInt) {
                luckyInt = entry.getKey();
            }
        }
        return luckyInt;
    }
}