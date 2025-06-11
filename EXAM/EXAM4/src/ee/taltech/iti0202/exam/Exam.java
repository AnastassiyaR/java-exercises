package ee.taltech.iti0202.exam;

import java.util.List;

public class Exam {


    private static final  int THELOWFLOAT = -999;
    private static final  int AGELIMIT = 21;
    private static final  double INCOMELIMIT = 25000.0;
    private static final  int CREDITSCORELIMIT = 600;
    private static final  double DEBTLIMIT = 0.4;

    /**
     * Returns true if the person meets the eligibility criteria for a loan.
     * <p>
     * In order to be eligible for a loan, a person must:
     * - be 21 years old or older
     * - have an annual income of 25000 or more
     * - have a credit score of 600 or more
     * - be employed of self-employed
     * - have a debt to income ratio that's less than 0.4 (an income of 10000 and debt of 4000 gives a
     * debt to income ratio of 0.4)
     *
     * @param age         the applicant's age
     * @param income      the applicant's annual income in dollars
     * @param creditScore the applicant's credit score (300-850)
     * @param employment  the applicant's employment status ("employed", "unemployed" or "self-employed")
     * @param debt        the applicant's total existing debt in dollars
     * @return true if eligible; false otherwise
     */
    public static boolean isEligible(int age, double income, int creditScore, String employment, double debt) {
        if (age < AGELIMIT) return false;
        else if (income < INCOMELIMIT) {
            return false;
        } else if (creditScore < CREDITSCORELIMIT) {
            return false;
        } else if (employment != "employed" && employment != "self-employed") {
            return false;
        }

        double sum = debt / income;
        if (sum >= DEBTLIMIT) {
            return false;
        }
        return true;
    }


    /**
     * Finds the second largest unique number in a list of positive integers.
     * If the list is empty or there is no second largest unique value, returns -1.
     *
     * @param numbers a list of positive integers (may contain duplicates)
     * @return the second largest unique number, or -1 if it cannot be determined
     */
    public static int findSecondLargest(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) return -1;
        int max = THELOWFLOAT;
        for (Integer number : numbers) {
            if (number > max) max = number;
        }

        int secondLargest = THELOWFLOAT;
        for (Integer number : numbers) {
            if ((number > secondLargest) && number != max) {
                secondLargest = number;
            }
        }

        if (secondLargest == THELOWFLOAT) return -1;

        return secondLargest;
    }
    public static void main(String[] args) {
        System.out.println(findSecondLargest(List.of(4, 1, 3, 2)));
    }
}
