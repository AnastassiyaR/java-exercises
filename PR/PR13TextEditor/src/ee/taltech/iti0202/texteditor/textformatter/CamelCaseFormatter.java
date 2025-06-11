package ee.taltech.iti0202.texteditor.textformatter;

public class CamelCaseFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        if (text == null || text.isBlank()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean capitilize = false;
        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            if (Character.isLetterOrDigit(c)) {
                if (capitilize) {
                    c = Character.toUpperCase(c);
                    capitilize = false;
                }
            } else {
                capitilize = true;
                if (i != text.length() - 1 && !(i == text.length() - 2 && text.charAt(i + 1) == '\n')) {
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
