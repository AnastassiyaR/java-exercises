package ee.taltech.iti0202.validation;

import java.util.Arrays;

public class PasswordValidator {
    public int MAX = 64; // no magic numbers
    public int MIN = 8; // no magic numbers

    /**
     * Check if the password's length is within the valid range.
     * The password should have a length between 8 and 64 symbols.
     * @param password Password to be checked
     * @return true, if password's length is within the valid range, false otherwise
     */

    public boolean isCorrectLength(String password) {
        if (MIN <= password.length() && password.length() <= MAX) {
            return true;
        }
        return false;
    }
    /**
     * Check if the password contains at least one uppercase letter.
     * @param password Password to be checked
     * @return true, if the password contains at least one uppercase letter, false otherwise
     */
    public boolean includesUppercase(String password) {
        return !password.equals(password.toLowerCase());
    }

    /**
     * Check if the password contains at least one lowercase letter.
     * @param password Password to be checked
     * @return true, if the password contains at least one lowercase letter, false otherwise
     */
    public boolean includesLowercase(String password) {
        return !password.equals(password.toUpperCase());
    }

    /**
     * Check if the password contains at least one special character
     * (whitespace is also considered a special character).
     * @param password Password to be checked
     * @return true, if the password contains at least one special character, false otherwise
     */
    public boolean includesSpecial(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (!Character.isLetterOrDigit(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the password contains at least one numeric digit.
     * @param password Password to be checked
     * @return true, if the password contains at least one numeric digit, false otherwise
     */
    public boolean includesNumber(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the current password is different enough from the old password.
     * <p>
     * The overlap between the new password and old password should be less than 50%
     * The check for overlap is case-insensitive
     * The overlap is also checked for the reversed version of the new password
     * <p>
     * Example:
     * Old password: "password123"
     * New password: "DrOwN540"
     * Should return false because "DrOw" is 50% of the new password and is present in the old password
     * @param oldPassword The old password
     * @param newPassword The new password
     * @return true, if the new password is different enough, False otherwise
     */

    public boolean isDifferentFromOldPassword(String oldPassword, String newPassword) {
        String oldpass = oldPassword.toLowerCase();
        String newpass = newPassword.toLowerCase();

        int half = newpass.length();
        if (half % 2 == 0) {
            half = half / 2;
        } else {
            half = (half + 1) / 2;
        }

        // half = (half % 2 == 0) ? (half / 2) : ((half + 1) / 2);

        for (int i = 0; i <= newpass.length() - half; i++) {
            String sub = newpass.substring(i, i + half);
            String reversedSub = new StringBuilder(sub).reverse().toString();

            if (oldpass.contains(sub) || oldpass.contains(reversedSub)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the password contains the name of the account owner.
     *
     * The name received as input may contain whitespace to separate the first and last name,
     * neither of which should be present in the password.
     * If the name contains a hyphen (ie. Mari-Liis), neither part of the name should be present in the password.
     * The name should not be in the password even if the casing of it is different in the password.
     * Reversed format of the name is also not allowed in the password.
     * @param password The password to be validated
     * @param name The full name of account owner
     * @return true, if the name is present in the password, false otherwise
     */
    public boolean isNameInPassword(String password, String name) {
        String newPassword = password.toLowerCase();
        String[] words = name.toLowerCase().split("[- ]");
        System.out.println(Arrays.toString(words));
        System.out.println("words " + words);
        System.out.println(newPassword);

        String reverse = "";
        for (int i = name.length() - 1; i >= 0; i--) {
            reverse += name.charAt(i);
        }

        String[] reversedwords = reverse.toLowerCase().split("[- ]");
        System.out.println(Arrays.toString(reversedwords));

        for (int i = 0; i < words.length; i++) {
            if (newPassword.contains(words[i]) || newPassword.contains(reversedwords[i])) {
                return true;
            }
        }
        return false;

    }

    /**
     * Check if the password contains the birthday of the account owner.
     *
     *
     * !! The day, month or year in the birth date cannot be present in the password.
     * !! For the birth year, the last two digits of the birth year separately are also not allowed.
     * For the day, month or last 2 digits of the year,
     * !! the reversed number is allowed but for the full 4-digit year is not allowed in the reverse format.
     *
     * The date is always in the format "dd.mm.yyyy", where
     *      dd is 2-digit day (01, 02, .. 31)
     *      mm is 2-digit month (01, 02, .. 12)
     *      yyyy is 4-digit year (0001, 0002, ..., 2022, 2023, ..., 3000, ...)
     *
     * You don't have to validate the date
     * @param password The password to be validated
     * @param birthDate Birth date of the account owner, format is dd.mm.yyyy
     * @return true, if the birthday is present in the password, false otherwise
     */
    public boolean isBirthdayInPassword(String password, String birthDate) {
        String reverse = "";
        for (int i = birthDate.length() - 1; i >= 0; i--) {
            reverse += birthDate.charAt(i);
        }
        String[] dates = birthDate.split("\\.");
        for (int i = 0; i < dates.length; i++) {
            if (password.contains(dates[i])
                    || password.contains(birthDate.substring(birthDate.length() - 2, birthDate.length()))
                    || password.contains(reverse.substring(0, 4))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the given password is valid.
     *
     * This function combines several checks to determine if the password is valid.
     * It checks the following:
     *  Password length,
     *  Presence of upper- and lowercase letters,
     *  Inclusion of at least one number,
     *  Inclusion of at least one special character,
     *  Absence of user's name and birth date in password,
     *  The password is at least 50% different from the old one.
     *
     * Call all the functions you wrote before within this one to complete the validation.
     * @param newPassword The password to be checked
     * @param oldPassword The previous password of the account
     * @param name The user's full name
     * @param birthDate The user's birth date
     * @return true if the password is valid, false otherwise
     */
    public boolean isPasswordValid(String newPassword, String oldPassword, String name, String birthDate) {
        if (isCorrectLength(newPassword)
                && includesSpecial(newPassword)
                && isDifferentFromOldPassword(oldPassword, newPassword)
                && includesUppercase(newPassword)
                && includesLowercase(newPassword)
                && includesSpecial(newPassword)
                && includesNumber(newPassword)
                && !isNameInPassword(newPassword, name)
                && !isBirthdayInPassword(newPassword, birthDate)) {
            return true;
        }
        return false;
    }


    // Below here are test cases.
    // Java program entry point - Your code executes from here
    public static void main(String[] args) {
        // Make class into usable object, otherwise we can't access what we wrote
        PasswordValidator validator = new PasswordValidator();

        System.out.println("Password length validation:");
        System.out.println(validator.isCorrectLength("kascnewi3r34t")); // true
        System.out.println(validator.isCorrectLength("%df#S1"));        // false
        System.out.println(validator.isCorrectLength(
                "kascn¤e%wi3r34tkj*bö ihvlc&?¤kfxyzsr<eq 3454566FGHJOI*UYUF& %¤##&TTRq6")); // false

        System.out.println("\nPassword has at least one uppercase letter validation:");
        System.out.println(validator.includesUppercase("Defwefwevwe"));     // true
        System.out.println(validator.includesUppercase("e/¤!fwe64fwevw"));  // false

        System.out.println("\nPassword has at least one lowercase letter validation:");
        System.out.println(validator.includesLowercase("dJOWE821%&/")); // true
        System.out.println(validator.includesLowercase("DJOWE821%&/")); // false

        System.out.println("\nPassword has at least one special character validation:");
        System.out.println(validator.includesSpecial("&smqwdp24DS"));   // true
        System.out.println(validator.includesSpecial("ksmqwd p24DS"));  // true
        System.out.println(validator.includesSpecial("ksmqwdp24DS"));   // false
        System.out.println(validator.includesSpecial(""));              // false

        System.out.println("\nPassword has at least one number validation:");
        System.out.println(validator.includesNumber("dJOWE8%&/"));  // true
        System.out.println(validator.includesNumber("dJOWE%&/"));   // false

        System.out.println("\nNew password is different from the old one validation:");
        System.out.println(validator.isDifferentFromOldPassword("õunamoos", "maasikamoos"));        // true
        System.out.println(validator.isDifferentFromOldPassword("olevsulev67", "ämblikmees18"));    // true
        System.out.println(validator.isDifferentFromOldPassword("seinav2rv", "seinakapp"));         // false
        System.out.println(validator.isDifferentFromOldPassword("merineitsi99", "mereneitsi11"));   // false
        System.out.println(validator.isDifferentFromOldPassword("eva1970", "0791ave"));             // false

        System.out.println("\nPassword doesn't have your name in validation:");
        System.out.println(validator.isNameInPassword("ddccwemelani", "Melani Mets"));              // true
        System.out.println(validator.isNameInPassword("ddccwinalemw", "Melani Mets"));              // true
        System.out.println(validator.isNameInPassword("ddccwsSTEMq", "Melani Mets"));               // true
        System.out.println(validator.isNameInPassword("ddccwinagregorq", "Karl-Gregor Mustikas"));  // true
        System.out.println(validator.isNameInPassword("ddccwinamustikas", "Karl-Gregor Mustikas")); // true
        System.out.println(validator.isNameInPassword("ddccws23%q", "Melani Mets"));                // false

        System.out.println("\nPassword doesn't have your birthdate validation");
        System.out.println(validator.isBirthdayInPassword("dd&&ccwe30", "30.04.2023")); // true
        System.out.println(validator.isBirthdayInPassword("dd&&ccwe03", "30.04.2023")); // false
        System.out.println(validator.isBirthdayInPassword("ddccw%2023", "30.04.2023")); // true
        System.out.println(validator.isBirthdayInPassword("ddccw%3202", "30.04.2023")); // true
        System.out.println(validator.isBirthdayInPassword("04ddccw%&1", "30.04.2023")); // true
        System.out.println(validator.isBirthdayInPassword("40ddccw%&1", "30.04.2023")); // false
        System.out.println(validator.isBirthdayInPassword("56ddccw%&1", "30.04.2023")); // false
        System.out.println(validator.isBirthdayInPassword("23ddccw%&1", "30.04.2023")); // true

        System.out.println("\nPassword is completely validated:");
        System.out.println(validator.isPasswordValid(
                "k45aLK%1",
                "SunsetBeach2022!",
                "Marek Põõsas",
                "26.06.2003")); // true
        System.out.println(validator.isPasswordValid(
                "keramRTYUY2003RDSCF.",
                "PurpleDragon42*",
                "Marek Põõsas",
                "12.04.2003")); // false
    }

}
