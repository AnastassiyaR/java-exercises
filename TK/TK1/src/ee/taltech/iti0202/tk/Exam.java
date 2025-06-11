package ee.taltech.iti0202.tk;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Collections.max;

public class Exam {


    /**
     * Calculate the discount percentage based on purchase amount and membership status.
     *
     * Discount rules:
     * - Members get 15% if purchase is $100 or more, otherwise 10%.
     * - Non-members get 5% if purchase is $100 or more, otherwise no discount.
     *
     * Examples:
     * calculateDiscount(120.0, true) -> 0.15
     * calculateDiscount(80.0, true) -> 0.10
     * calculateDiscount(120.0, false) -> 0.05
     * calculateDiscount(80.0, false) -> 0.0
     */
    public static double calculateDiscount(double purchaseAmount, boolean isMember) {
        if (isMember) {
            if (purchaseAmount > 100) {
                return 0.15;
            }
            else {
                return 0.10;
            }
        }
        else {
            if (purchaseAmount > 100) {
                return 0.05;
            }
            else {
                return 0.0;
            }
        }
    }


    /**
     * This method replaces all occurrences of a specified character in a string with another character.
     *
     * @param text The original string in which the character replacement will be made.
     * @param oldCharacter The character to be replaced.
     * @param replacement The character that will replace oldChar in the input string.
     * @return A new string with all occurrences of oldChar replaced by newChar.
     *
     * Example:
     * replaceCharacter("hello", 'l', 'x') -> "hexxo"
     * replaceCharacter("banana", 'a', 'o') -> "bonono"
     */
    public static String replaceGivenCharacter(String text, Character oldCharacter, Character replacement) {
        return text.replaceAll(oldCharacter.toString(), replacement.toString());
    }

    /**
     * Finds the maximum difference between an element in the list and any element before it.
     * The function iterates through the list, calculates the difference between the current element
     * and the smallest value found before it, and returns the largest positive difference.
     * If the list has fewer than two elements, the function returns -1 because a valid difference cannot be calculated.
     * If no positive difference is found, it will return -1.
     *
     * @param numbers A list of integers.
     * @return The maximum positive difference between an element and a prior smaller element.
     *         Returns -1 if no valid difference exists or the list has fewer than two elements.
     */
    public static int findMaxDifference(List<Integer> numbers) {

        int maxDifference = 0;
        int max_num = max(numbers);

        if (max_num == numbers.indexOf(0)) {
            return -1;
        }

        if (numbers.size() < 2 || numbers.isEmpty()) {
            return -1;
        }
        if (max_num < 0) {
            return -1;
        }

        for (int i = 0; i < numbers.indexOf(max_num); i++) {
            if (max_num - numbers.get(i) > maxDifference) {
                maxDifference = max_num - numbers.get(i);
            }
        }
        if (maxDifference <= 0) {
            return -1;
        }
        return maxDifference;
    }


    public static void main(String[] args) {
        System.out.println(findMaxDifference(Arrays.asList(2, 3, 10, 6, 4, 8, 1)));  // 8
        System.out.println(findMaxDifference(Arrays.asList(7, 6, 5, 4, 3, 2, 1)));  // -1
        System.out.println(findMaxDifference(Arrays.asList(1, 2, 3, 4, 5)));  // 4
        System.out.println(findMaxDifference(Arrays.asList(-2, 5, 3, -1, 4)));  // 7
        System.out.println(findMaxDifference(Arrays.asList(5, 5, 5, 5, 5)));  // -1
        System.out.println(findMaxDifference(Arrays.asList(1, 100, 50, 10, 5)));  // 99
    }
}
