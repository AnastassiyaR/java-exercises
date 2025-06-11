package ee.taltech.iti0202.texteditor.textformatter;

public class BinaryFormatter implements TextFormatter {

    private static final String BITS = "00000000";
    @Override
    public String format(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\n' && (i == chars.length - 1)) {
                sb.append("\n");
                break;
            }
            String binary = Integer.toBinaryString(c);
            binary = BITS.substring(binary.length()) + binary;
            sb.append(binary);
        }
        return sb.toString();
    }
}
