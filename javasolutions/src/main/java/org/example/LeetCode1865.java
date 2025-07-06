// LeetCode1865: Finding Pairs With a Certain Sum
package org.example;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LeetCode1865 {

    public static void main(String[] args) {
        System.out.println("LeetCode 1865: Find Pairs With a Certain Sum");
        Scanner sc = new Scanner(System.in);

        // Read first array
        System.out.println("Enter the length of the 1st Array array");
        int size = sc.nextInt();
        int[] arr1 = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.printf("Enter your %d value to load into 1st array %n ", i + 1);
            arr1[i] = sc.nextInt();
        }

        // Read second array
        System.out.println("Enter the length of the 2nd Array array");
        size = sc.nextInt();
        int[] arr2 = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.printf("Enter your %d value to load into 2nd array %n ", i + 1);
            arr2[i] = sc.nextInt();
        }

        FindSumPairs obj = new FindSumPairs(arr1, arr2);
        boolean continueLoop = true;
        while (continueLoop) {
            printMenu();
            String choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("Enter the value to count: ");
                    int valueToCount = sc.nextInt();
                    int count = obj.count(valueToCount);
                    System.out.println("Count of pairs with sum " + valueToCount + " is: " + count);
                    break;
                case "2":
                    System.out.println("Enter index and value to add: ");
                    int index = sc.nextInt();
                    System.out.printf("\nEnter value to add at index %d: ", index);
                    int value = sc.nextInt();
                    obj.add(index, value);
                    System.out.println("Value added successfully.");
                    break;
                case "3":
                    System.out.println("Null");
                    break;
                default:
                    System.out.println("Invalid choice. Stopping the program.");
                    continueLoop = false;
            }

        }

    }

    private static void printMenu() {
        System.out.println("choose operation to perform: 1. Count, 2. Add, 3.FindSumPairs, 4. q to quit");
    }

    // Helper class to manage the pair operations
    public static class FindSumPairs {
        private final int[] nums1;
        private final int[] nums2;
        Map<Integer, Integer> frequencyMap;

        public FindSumPairs(@Nonnull int[] nums1, @Nonnull int[] nums2) {
            this.nums1 = nums1;
            this.nums2 = nums2;
            frequencyMap = new HashMap<>();
            // Build frequency map for nums2
            for (int num : nums2) {
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }

        }

        // Adds value to nums2[index] and updates the frequency map
        public void add(int index, int value) {
            int oldValue = nums2[index];
            frequencyMap.put(oldValue, frequencyMap.get(oldValue) - 1);
            if (frequencyMap.get(oldValue) == 0) {
                frequencyMap.remove(oldValue);
            }
            nums2[index] += value;
            frequencyMap.put(nums2[index], frequencyMap.getOrDefault(nums2[index], 0) + 1);
        }


        // Counts the number of pairs with the given sum
        public int count(int value) {
            int count = 0;
            for (int num : nums1) {
                int complement = value - num;
                count += frequencyMap.getOrDefault(complement, 0);
            }
            return count;
        }
    }
}
