package ee.taltech.iti0202.exam;

public class Exam {
    /**
     * Counts the number of symmetric pairs in a string relative to its center.
     * A pair is symmetric if characters at equal distances from the center are identical.
     * If the string length is odd, the central character is ignored.
     * Examples:
     * symmetricPairs("abba") -> Should output: 2
     * symmetricPairs("abcda") -> Should output: 1
     *
     * @param string Input string
     * @return Number of symmetric pairs
     */
    public static int symmetricPairs(String string) {
        if (string == null) return 0;
        int count = 0;
        int length = string.length();
        int half = length / 2;
        for (int i = 0; i < half; i++) {
            if (string.charAt(i) == string.charAt(length - 1 - i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Task: Implement Run-Length Encoding (RLE) algorithm.
     *
     * Given a string, encode it by replacing consecutive repeated characters with the character followed by the count.
     * If a character appears only once, do not append the count.
     * Uppercase and lowercase letters are considered different characters.
     *
     * Example:
     * Input: "aaabbcddddd"
     * Output: "a3b2cd5"
     */
    public static String encodeWithRLE(String input) {
        if (input == null || input.isEmpty()) return "";

        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                count++;
            } else {
                result.append(input.charAt(i - 1));
                if (count > 1) {
                    result.append(count);
                }
                count = 1;
            }
        }
        result.append(input.charAt(input.length() - 1));
        if (count > 1) {
            result.append(count);
        }

        return result.toString();
    }
}
