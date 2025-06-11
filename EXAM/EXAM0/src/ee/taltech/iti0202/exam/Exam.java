package ee.taltech.iti0202.exam;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Exam {

    /**
     * 01
     *
     * For each multiple of 10 in the given array,
     * change all the values following it to be that multiple of 10,
     * until encountering another multiple of 10.
     * So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.
     *
     * tenRun([2, 10, 3, 4, 20, 5]) => [2, 10, 10, 10, 20, 20]
     * tenRun([10, 1, 20, 2]) => [10, 10, 20, 20]
     * tenRun([10, 1, 9, 20]) => [10, 10, 10, 20]
     */
    public static List<Integer> tenRun(List<Integer> nums) {
        List<Integer> result = new ArrayList<>(nums);
        Integer currentMultiple = null;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) % 10 == 0) {
                currentMultiple = result.get(i);
            } else if (currentMultiple != null) {
                result.set(i, currentMultiple);
            }
        }
        return result;
    }

    /**
     * 02
     *
     * Write a method that analyzes input String and returns all pairs of same letter that is present
     * as lower-case and upper-case in input String.
     * Returned letter pairs have to be in alphabetic order.
     * If there are multiple same letter pairs, then return only one. If there are no suitable pairs, return "".
     * Take latin alphabet 'a' - 'z' as base.
     * mixedPairs("abcABD") => "AaBb" (a and b are represented by both lowe and upper cased letter)
     * mixedPairs("aaaAAAaaaa") => "Aa"
     * mixedPairs("tere") => ""
     * mixedPairs("bBaacA") => "AaBb" (result is in alphabetic order, although in input String, b is earlier than a).
     * @param input
     * @return
     */
    public static String mixedPairs(String input) {
        Set<String> lowerCase = new HashSet<>();
        Set<String> upperCase = new HashSet<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                upperCase.add(input.charAt(i) + "");
            } else if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') {
                lowerCase.add(input.charAt(i) + "");
            }
        }
        System.out.println(lowerCase);
        List<String> res = lowerCase.stream()
                .filter(x -> upperCase.contains(x.toUpperCase()))
                .sorted()
                .toList();
        for (String x : res) {
            result.append(x.toUpperCase());
            result.append(x);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Set<String> uniqueFruits = Set.of("Apple", "Banana", "Cherry");
        System.out.println(uniqueFruits.stream().sorted().toList());

    }
}
