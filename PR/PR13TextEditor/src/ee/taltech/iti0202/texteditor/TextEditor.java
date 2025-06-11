package ee.taltech.iti0202.texteditor;

import ee.taltech.iti0202.texteditor.textformatter.TextFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TextEditor {

    private TextFormatter stragery;
    private List<String> history = new ArrayList<>();
    private int undoStep = 0;

    /**
     * Add text
     * @param text
     */
    public void addText(String text) {
        addText(text, null);
    }

    /**
     * Add text with a param
     * @param text
     * @param type
     */
    public void addText(String text, TextType type) {
        if (text == null || text.isEmpty()) {
            return;
        }

        if (type != null) {
            setStrategy(type);
        }

        if (undoStep > 0) {
            history = history.subList(0, history.size() - undoStep);
            undoStep = 0;
        }

        if (stragery != null) {
            text = stragery.format(text);
        }

        history.add(text);
    }

    /**
     * Get current text
     * @return text
     */
    public String getCurrentText() {
        if (history.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean endWithNewline = false;
        for (int i = 0; i < history.size() - undoStep; i++) {
            String text = history.get(i);
            if (!sb.toString().isEmpty() && !text.startsWith("\n") && !endWithNewline) {
               sb.append(" ");
            }
            sb.append(text);
            endWithNewline = text.endsWith("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Undo text
     * @return text
     */
    public String undo() {
        if (history.size() > 1) {
            undoStep++;
        }
        return getCurrentText();
    }

    /**
     * Redo text
     * @return text
     */
    public String redo() {
        if (undoStep > 0) {
            undoStep--;
        }
        return getCurrentText();
    }

    /**
     * Clear history
     */
    public void clear() {
        history.clear();
        undoStep = 0;
    }

    /**
     * Set stragery
     * @param type
     */
    public void setStrategy(TextType type) {
        stragery = type.getFormatter();
    }

    /**
     * Get history
     * @return history
     */
    public Collection<String> getHistory() {
        return history;
    }

    /**
     * Get strategy
     * @return stragery
     */
    public TextFormatter getStrategy() {
        return stragery;
    }
}
