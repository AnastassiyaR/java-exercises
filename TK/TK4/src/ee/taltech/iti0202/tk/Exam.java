package ee.taltech.iti0202.tk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exam {


    /**
     * Given 2 int values greater than 0, return whichever value is nearest to 21 without going over.
     * Return 0 if they both go over.
     * <p>
     * blackjack(19, 21) → 21
     * blackjack(21, 19) → 21
     * blackjack(19, 22) → 19
     */
    public static int blackjack(int a, int b) {
        if (a > 21 && b > 21) return 0;
        if (a > 21) return b;
        if (b > 21) return a;
        return Math.max(a, b);
    }


    /**
     * This method identifies the first repeating character in the given string.
     * If no repeating character is found or the input is null, the method returns null.
     *
     * @param input The string to be checked for repeating characters.
     * @return The first repeating character if found; otherwise, null.
     */
    public static Character findFirstRepeatingChar(String input) {
        if (input == null) return null;
        Set<Character> seen = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (seen.contains(c)) {
                return c;
            }
            seen.add(c);
        }
        return null;
    }


    /**
     * Computes the cumulative sum of a list of integers.
     *
     * @param numbers A list of integers.
     * @return A new list where each element is the cumulative sum of the previous elements.
     */
    public static List<Integer> cumulativeSum(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        int sum = 0;
        for (int num : numbers) {
            sum += num;
            result.add(sum);
        }
        return result;
    }
}
