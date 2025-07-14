//LeetCode 1290: Convert Binary Number in a Linked List to Integer
package org.example;

import java.util.Scanner;

public class LeetCode1290 {

    static class Node {
        int val;
        Node next;

        Node(int value) {
            this.val = value;
            this.next = null;
        }
    }

    static class LinkedList {
        Node head;

        public void addToList(int value) {
            if (head == null) {
                head = new Node(value);
            } else {
                Node curr = head;
                while (curr.next != null) {
                    curr = curr.next;
                }
                curr.next = new Node(value);
            }
        }

        public void printList() {
            Node curr = head;
            StringBuilder sc = new StringBuilder();
            while (curr != null) {
                sc.append(curr.val);
                curr = curr.next;
            }
            System.out.println("Entered Binary Value : " + sc);
        }

        public int binaryToInteger() {
            Node curr = head;
            int result = 0;
            while (curr != null) {
                result = (result << 1) | curr.val;
                curr = curr.next;
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter the size of linked list");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList lL = new LinkedList();
        for (int i = 0; i < n; i++) {
            System.out.println("Add the " + (i + 1) + " value to linked list");
            int value = sc.nextInt();
            lL.addToList(value);
        }
        sc.close();
        lL.printList();
        int result = lL.binaryToInteger();
        System.out.println("Integer value of above binary is : " + result);

    }
}
