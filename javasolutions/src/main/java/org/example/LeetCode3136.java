package org.example;

import java.util.Scanner;

//LeetCode 3136: Valid Word
public class LeetCode3136 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the word to check if it is valid: ");
        String word = sc.nextLine();
        sc.close();

        boolean isValid = isValidWord(word);
        System.out.println("Is the word valid? " + isValid);
    }

    private static boolean isValidWord(String word) {

        //check if the word is greater than 3 or null
        if (word == null || word.length() < 3) {
            return false;
        }
        boolean isVowel = false;
        boolean isConsonant = false;

        for (char c : word.toCharArray()) {
            //check if the character is not a letter or digit
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
            if (Character.isLetter(c)) {
                // check if character is a vowel
                if ("aeiouAEIOU".indexOf(c) == -1) {
                    isVowel = true;
                } else {
                    isConsonant = true;
                }
            }
        }
        return isVowel && isConsonant;
    }
}
