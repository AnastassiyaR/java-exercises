package ee.taltech.iti0202.exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exam {

    /**
     * Determines if the given string {@code s} can be shifted any number of times
     * to match the string {@code goal}. A shift operation consists of moving the
     * first character of the string to the end.
     *
     * @param s    The original string to be shifted.
     * @param goal The target string to check against.
     * @return true if {@code s} can be shifted to become {@code goal}, false otherwise.
     */

    public static boolean canShift(String s, String goal) {
        // abcabca
        List<String> letters = new ArrayList<>();
        String result = s;
        if  (s.equals(goal)) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            result += s;
            if (result.contains(goal)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the given string {@code s} can be shifted any number of times
     * to match the string {@code goal}. A shift operation consists of moving the
     * first character of the string to the end.
     *
     * @param s The original string to be shifted.
     * @return true if {@code s} can be shifted to become {@code goal}, false otherwise.
     */
    public static List<String> canBeWrittenInOneRow(List<String> s) {
        String firstrow = "qwertyuiop";
        String secondrow = "asdfghjkl";
        String thirdrow = "zxcvbnm";
        List<String> result = new ArrayList<>();

        String first = "";
        String second = "";
        String third = "";
        boolean flag = false;

        int count = 0;

        for (String row : s) {
            for (int i = 0; i < row.length(); i++) {
                String c = String.valueOf(row.charAt(i));
                if (firstrow.contains(c)) {
                    first += c;
                } else if (secondrow.contains(c)) {
                    second += c;
                } else if (thirdrow.contains(c)) {
                    third += c;
                }
            }
            if (!first.isEmpty()) {
                count++;
            }
            if (!second.isEmpty()) {
                count++;
            }
            if (!third.isEmpty()) {
                count++;
            }

            if (count == 1) {
                result.add(row);
            }

            System.out.println("Count : " + count);
            first = "";
            second = "";
            third = "";
            count = 0;

        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(canShift("abcde", "cdeab"));
        System.out.println(canBeWrittenInOneRow(List.of("omk")));
    }

}