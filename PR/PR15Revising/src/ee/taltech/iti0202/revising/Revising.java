package ee.taltech.iti0202.revising;

import java.util.List;

public class Revising {

    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int MINUSONE = -1;

    /**
     * Return true if the list contains, somewhere, three increasing adjacent numbers
     * like .... 4, 5, 6, ... or 23, 24, 25.
     *
     * tripleUp(List.of(1, 4, 5, 6, 2)) => true
     * tripleUp(List.of(1, 2, 3)) => true
     * tripleUp(List.of(1, 2, 4)) => false
     *
     * @param numbers List of integers.
     * @return Whether the list contains adjacent numbers.
     */
    public static boolean tripleUp(List<Integer> numbers) {
        for (int i = 0; i < numbers.size() - TWO; i++) {
            int first = numbers.get(i);
            int second = numbers.get(i + 1);
            int third = numbers.get(i + TWO);
            if (second == first + 1 && third == second + 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Given three ints, a b c, one of them is small, one is medium and one is large.
     *
     * Return true if the three values are evenly spaced,
     * so the difference between small and medium is the same as the difference between medium and large.
     *
     * evenlySpaced(2, 4, 6) => true
     * evenlySpaced(4, 6, 2) => true
     * evenlySpaced(4, 6, 3) => false
     */
    public static boolean evenlySpaced(int a, int b, int c) {
        int small = Math.min(Math.min(a, b), c);
        int large = Math.max(Math.max(a, b), c);
        int medium = a + b + c - small - large;

        return (medium - small) == (large - medium);
    }

    /**
     * Given a list of integers,
     * return true if the value 3 appears in the list exactly 3 times,
     * and no 3's are next to each other.
     *
     * haveThree([3, 1, 3, 1, 3]) => true
     * haveThree([3, 1, 3, 3]) => false
     * haveThree([3, 4, 3, 3, 4]) => false
     */
    public static boolean haveThree(List<Integer> numbers) {
        int count = 0;
        boolean adjacent = false;

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == THREE) {
                count++;
                if (i > 0 && numbers.get(i - 1) == THREE) {
                    adjacent = true;
                }
            }
        }

        return count == THREE && !adjacent;
    }

    /**
     * Given a string, consider the prefix string made of the first N chars of the string.
     * Does that prefix string appear somewhere else in the string.
     * Assume that the string is not empty and that N is in the range 1 .. str.length().
     *
     * prefixExistsAgain("abXXabc", 1) => true
     * prefixExistsAgain("abXXabc", 2) => true
     * prefixExistsAgain("abXXabc", 3) => false
     * prefixExistsAgain("ababa", 3) => true
     */
    public static boolean prefixExistsAgain(String text, int n) {
        if (n <= 0 || n > text.length()) {
            return false;
        }
        String prefix = text.substring(0, n);
        return text.indexOf(prefix, 1) != MINUSONE;
    }

    /**
     * Given lists nums1 and nums2 of the same length,
     * for every element in nums1, consider the corresponding
     * element in nums2 (at the same index).
     * Return the count of the number of times
     * that the two elements differ by 2 or less, but are not equal.
     *
     * matchUp([1, 2, 3], [2, 3, 10]) => 2
     * matchUp([1, 2, 3], [2, 3, 5]) => 3
     * matchUp([1, 2, 3], [2, 3, 3]) => 2
     */
    public static int matchUp(List<Integer> a, List<Integer> b) {
        int count = 0;
        for (int i = 0; i < a.size(); i++) {
            int diff = Math.abs(a.get(i) - b.get(i));
            if (diff > 0 && diff <= TWO) {
                count++;
            }
        }
        return count;
    }
}
