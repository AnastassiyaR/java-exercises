package ee.taltech.iti0202.texteditor.textformatter;

import java.util.List;

public class TitleCaseFormatter implements TextFormatter {

    private static final List<String> IGNORE = List.of("a", "an", "of", "the", "or", "and");
    @Override
    public String format(String text) {
        if (text == null || text.isBlank()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] words = text.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i].trim().toLowerCase();
            if (i == 0 || i == words.length - 1 || !IGNORE.contains(word)) {
                sb.append(word.substring(0, 1).toUpperCase());
                sb.append(word.substring(1));
            } else {
                sb.append(word);
            }
            sb.append(" ");
        }
        return sb.toString().trim() + "\n";
    }
}
