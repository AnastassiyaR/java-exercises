package ee.taltech.iti0202.tk;



import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.min;

public class Exam {


    /**
     * Evaluate the grade based on the provided score.
     *
     * Requirements:
     * - Input Validation: If the score is not within the range 0 to 100, return `"Invalid score"`
     * - Grading Criteria:
     *   - 90 to 100: Return `"Grade A"`
     *   - 80 to 89: Return `"Grade B"`
     *   - 70 to 79: Return `"Grade C"`
     *   - 60 to 69: Return `"Grade D"`
     *   - Below 60: Return `"Grade F"`
     *
     * @param score An integer representing the student's score.
     * @return A string containing the grade.
     */
    public static String evaluateGrade(int score) {
        final int ScoreA = 100;
        final int ScoreA2 = 90;
        final int ScoreB = 89;
        final int ScoreB2 = 80;
        final int ScoreC = 79;
        final int ScoreC2 = 70;
        final int ScoreD = 69;
        final int ScoreD2 = 60;
        final int ScoreFF = 0;

        if (ScoreA2 <= score && score <= ScoreA) {
            return "Grade A";
        } else if (ScoreB2 <= score && score <= ScoreB) {
            return "Grade B";
        } else if (ScoreC2 <= score && score <= ScoreC) {
            return "Grade C";
        } else if (ScoreD2 <= score && score <= ScoreD) {
            return "Grade D";
        } else if (ScoreFF <= score && score < ScoreD2) {
            return "Grade F";
        }
        return "Invalid score";
    }

    /**
     * Count the number of vowels and consonants in a given string.
     * Return an array of vowel and consonant count pair [vowels, consonants]
     *
     * Vowels are the letters a, e, i, o, u (both lowercase and uppercase).
     * All other alphabetic characters are considered consonants.
     *
     * Examples:
     * countVowelsAndConsonants("Hello") -> [2, 3]
     * countVowelsAndConsonants("Java") -> [2, 2]
     * countVowelsAndConsonants("AEIOU") -> [5, 0]
     * countVowelsAndConsonants("BCDFG") -> [0, 5]
     */
    public static int[] countVowelsAndConsonants(String input) {
        String vowels = "eoüõaöäui";
        int countVowel = 0;
        int countConst = 0;

        for (int i = 0; i < input.length(); i++) {
            String inp = String.valueOf(input.charAt(i));
            if (vowels.contains(inp.toLowerCase())) {
                countVowel++;
            } else {
                countConst++;
            }
        }
        int[] res = new int[2];
        res[0] = countVowel;
        res[1] = countConst;
        return res;
    }

    /**
     * Find the second-smallest element in a given list of integers and return it.
     *
     * The second-smallest element is the smallest element after excluding the smallest.
     * If the list has fewer than 2 elements, an IllegalArgumentException is thrown.
     * NB! Don't try to remove any element from given numbers list!
     *
     * Examples:
     * findSecondSmallest([5, 1, 4, 2, 3]) -> 2
     * findSecondSmallest([10, 20, 30]) -> 20
     * findSecondSmallest([1, 1, 2, 2]) -> 2
     * findSecondSmallest([5]) -> IllegalArgumentException
     *
     * Hint:
     * Use Integer.MAX_VALUE to initialize variables for tracking the smallest and second-smallest elements.
     */
    public static int findSecondSmallest(List<Integer> numbers) {

        List<Integer> checking = new ArrayList<>();
        for (int i : numbers) {
            checking.add(i);
        }

        if (checking.size() < 2) {
            throw new IllegalArgumentException();
        }

        int maxNum = min(checking);

            while (checking.contains(maxNum)) {
                for (int i = 0; i < checking.size(); i++) {
                if (checking.get(i) == maxNum) {
                    checking.remove(checking.get(i));
                }
            }
        }

        int check = min(checking);
        return check;
    }
}
