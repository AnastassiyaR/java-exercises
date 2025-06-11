package ee.taltech.iti0202.tk;

import java.util.ArrayList;
import java.util.List;

public class Exam {

    /**
     * Method gets three numbers representing the sides of a triangle and returns the corresponding triangle type.
     * Outcome is "Invalid triangle" If any side is less or equal to zero.
     * Outcome is "Equilateral triangle" If all sides are equal.
     * Outcome is "Isosceles triangle" If exactly two sides are equal.
     * Outcome is "Scalene triangle" If all sides are different.
     *
     * @param a Integer representing the first side of the triangle.
     * @param b Integer representing the second side of the triangle.
     * @param c Integer representing the third side of the triangle.
     * @return String indicating the triangle type.
     */
    public static String determineTriangleType(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return "Invalid triangle";
        } else if (a == b && b == c) {
            return "Equilateral triangle";
        } else if (b == a || a == c || c == b) {
            return "Isosceles triangle";
        } else {
            return "Scalene triangle";
        }
    }

    /**
     * Given word that contains only letters.
     * Return true if the word is palindrome, otherwise return false. (Case insensitive)
     * Palindrome is the word that reads the same backwards as forwards (wow, abba, deed).
     * isPalindrome("wow") => true
     * isPalindrome("woow") => true
     * isPalindrome("wOoW") => true
     * isPalindrome("mees") => false
     * isPalindrome("") => true
     *
     * @param word String to test
     * @return whether word is palindrome
     */
    public static boolean isPalindrome(String word) {
        String reversed = new StringBuilder(word).reverse().toString().toLowerCase();
        return word.toLowerCase().equals(reversed);
    }


    /**
     * Reorders the list in a spiral pattern.
     * <p>
     * For example, given [1, 2, 3, 4, 5, 6, 7, 8], returns [1, 8, 2, 7, 3, 6, 4, 5].
     *
     * @param numbers the original list of integers.
     * @return a new list with elements reordered in a spiral pattern.
     */
    public static List<Integer> spiralReorder(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        int left = 0;
        int right = numbers.size() - 1;

        while (left <= right) {
            if (left == right) {

                result.add(numbers.get(left));
            } else {

                result.add(numbers.get(left));
                result.add(numbers.get(right));
            }
            left++;
            right--;
        }

        return result;
    }
}
