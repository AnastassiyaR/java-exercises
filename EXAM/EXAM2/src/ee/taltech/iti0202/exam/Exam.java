package ee.taltech.iti0202.exam;

import java.util.*;

public class Exam {
    /**
     * Task: Count consecutive repeating groups in a list of integers.
     *
     * A group consists of at least two of the same number in a row.
     * Single values that do not repeat consecutively do not count as a group.
     *
     * @param numbers List of integers
     * @return Number of groups found
     */
    // 1 1 2 1 1
    public static int countGroups(List<Integer> numbers) {
        if (numbers.isEmpty() || numbers.size() == 1) {
            return 0;
        }
        int count = 0;
        int num = -9999;
        for (int i = 0; i < numbers.size() - 1; i++) {
            System.out.println(numbers.get(i) + " = " + numbers.get(i + 1));
            if (numbers.get(i) == numbers.get(i + 1)) {
                if (!(num == numbers.get(i))) {
                    count++;
                    num = numbers.get(i);
                }
            } else {
                num = -9999;
            }
        }
        return count;
    }


    /**
     * Task: Repeatedly remove adjacent identical letter pairs from a string.
     *
     * This process continues until no more adjacent pairs exist.
     * If the result is an empty string, return "empty".
     *

     * @return The reduced string, or "empty" if fully removed
     */
    public static String reducePairs(String input) {
        if (input == null || input.isEmpty()) {
            return "empty";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int len = result.length();

            if (len > 0 && result.charAt(len - 1) == c) {
                result.deleteCharAt(len - 1);
            } else {
                result.append(c);
            }
        }
        return result.length() == 0 ? "empty" : result.toString();
    }

    public static void main(String[] args) {
        System.out.println(countGroups(List.of(1, 1, 2, 1, 1)));
        System.out.println(countGroups(List.of(1, 2, 2, 3, 4, 4, 4, 5)));
        System.out.println(countGroups(List.of(5, 5, 5, 5)));

        System.out.println(reducePairs("abba"));
        System.out.println(reducePairs("aabcc"));

    }
}
