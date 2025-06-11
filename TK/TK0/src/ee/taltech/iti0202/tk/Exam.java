package ee.taltech.iti0202.tk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.max;
import static java.util.Collections.min;

public class Exam {


    /**
     * Return the "centered" average of an array of ints, which we'll say is the mean average of the values,
     * except ignoring the largest and smallest values in the array. If there are multiple copies of the
     * smallest value, ignore just one copy, and likewise for the largest value. Use int division to produce
     * the final average. You may assume that the array is length 3 or more.
     * <p>
     * centeredAverage([1, 2, 3, 4, 100]) → 3
     * centeredAverage([1, 1, 5, 5, 10, 8, 7]) → 5
     * centeredAverage([-10, -4, -2, -4, -2, 0]) → -3
     */
    public static int centeredAverage(List<Integer> nums) {
        int maxNum = max(nums);
        int minNum = min(nums);

        List<Integer> res = new ArrayList<>();
        res.addAll(nums);

        res.remove(res.indexOf(maxNum));
        res.remove((Integer) minNum);
        int sum = 0;
        for (Integer num : res) {
            sum += num;
        }
        return sum / res.size();
    }

    /**
     * A sandwich is two pieces of bread with something in between. Return the string that is between the first and
     * last appearance of "bread" in the given string, or return the empty string ""
     * if there are not two pieces of bread.
     * <p>
     * getSandwich("breadjambread") → "jam"
     * getSandwich("xxbreadjambreadyy") → "jam"
     * getSandwich("xxbreadyy") → ""
     */
    public static String getSandwich(String str) {

        String res = str;

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            int c = str.indexOf("bread");
            if (c != -1) {
                count = count + 1;
                str = str.substring(c + 5, str.length());
            } else {
                break;
            }
        }
        if (count < 2) {
            return "";
        }

        int lastIndex = res.lastIndexOf("bread");
        int firstIndex = res.indexOf("bread");
        return res.substring(firstIndex + 5, lastIndex);
    }

    /**
     * Given a map of food keys and topping values, modify and return the map as follows: if the key
     * "ice cream" is present, set its value to "cherry". In all cases, set the key "bread"
     * to have the value "butter".
     * <p>
     * <p>
     * topping({"ice cream": "peanuts"}) → {"bread": "butter", "ice cream": "cherry"}
     * topping({}) → {"bread": "butter"}
     * topping({"pancake": "syrup"}) → {"bread": "butter", "pancake": "syrup"}
     */
    public static Map<String, String> topping(Map<String, String> map) {
        HashMap<String, String> result = new HashMap<>();
        result.put("bread", "butter");
        System.out.println(result);
        // map.keySet() and map.values()
        // Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (String key : map.keySet()) {
            if (!(key == "bread")) {
                if (key == "ice cream") {
                    result.put(key, "cherry");
                } else {
                    result.put(key, map.get(key));
                }
            }
        }
        return result;
    }
}
