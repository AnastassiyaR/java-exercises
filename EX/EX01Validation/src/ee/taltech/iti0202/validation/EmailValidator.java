package ee.taltech.iti0202.validation;


public class EmailValidator {

    public boolean hasAtSymbol(String email) {
        if (email.contains("@")) {
            return true;
        }
        return false;
    }

    public boolean isValidUsername(String email) {
        int count = 0;
        int at = 0;

        for (int i = 0; i < email.length(); i++) {
            if (!Character.isLetterOrDigit(email.charAt(i)) && email.charAt(i) == '@') {
                at++;
                // System.out.println("at" + " " + email.charAt(i));
            } else if (!Character.isLetterOrDigit(email.charAt(i)) && email.charAt(i) != '.') {
                count++;
                // System.out.println("count" + " " + email.charAt(i));
            }
        }
        if (count == 0 && at == 1) {
            return true;
        }
        return false;
    }

    public String findDomain(String password) {
        int domain = password.lastIndexOf("@");
        return password.substring(domain + 1);
    }

    public boolean isValidDomain(String password) {
        int domainat = password.lastIndexOf("@");
        String domain = password.substring(domainat + 1);
        int count = 0;
        int point = 0;
        for (char c : domain.toCharArray()) {
            if (c == '.' && point == 0) {
                point++;
            } else if (c == '.' && point == 1
                    || !Character.isLetterOrDigit(c) && c != '.'
                    || Character.isDigit(c)) {
                count++;
            }
        }
        if (!(count == 0 && point == 1)) {
            return false;
        }

        // @-sümboli ja punkti vahel tohib olla 3-10 tähemärki
        // Pärast punkti tphib olla 2-5 tähemärki

        int lastat = password.lastIndexOf("@");
        int lastpoint = password.lastIndexOf(".");
        if (3 <= password.substring(lastat + 1, lastpoint).length()
                && password.substring(lastat + 1, lastpoint).length() <= 10
                && 2 <= password.substring(lastpoint + 1).length()
                && password.substring(lastpoint + 1).length() <= 5) {
            return true;
        }
        return false;
    }

    public boolean isValidEmailAddress(String email) {
        if (isValidDomain(email) && isValidUsername(email)) {
            return true;
        }
        return false;
    }


    public String createEmailAddress(String domain, String username) {
        String email = username + "@" + domain;

        if (isValidDomain(email) && isValidUsername(email)) {
            return email;
        }
        return "Cannot create a valid email address using the given parameters!";
    }

    public static void main(String[] args) {
        EmailValidator validator = new EmailValidator();


        System.out.println("\nCreate your own email addess");

        System.out.println(validator.createEmailAddress("hot.ee", "vana.ema"));         // vana.ema@hot.ee
        System.out.println(validator.createEmailAddress("jaani.org", "lennakuurma"));   // lennakuurma@jaani.org
        System.out.println(validator.createEmailAddress("koobas.com", "karu&pojad"));   // no valid

    }

}
