package ee.taltech.iti0202.texteditor;

import ee.taltech.iti0202.texteditor.textformatter.CamelCaseFormatter;
import ee.taltech.iti0202.texteditor.textformatter.TextFormatter;
import ee.taltech.iti0202.texteditor.textformatter.TitleCaseFormatter;
import ee.taltech.iti0202.texteditor.textformatter.UppercaseFormatter;
import ee.taltech.iti0202.texteditor.textformatter.BinaryFormatter;

public enum TextType {
    PLAIN {
        @Override
        public TextFormatter getFormatter() {
            return null;
        }
    },
    SCREAMING {
        @Override
        public TextFormatter getFormatter() {
            return new UppercaseFormatter();
        }
    },
    TITLE {
        @Override
        public TextFormatter getFormatter() {
            return new TitleCaseFormatter();
        }
    },
    CAMELCASE {
        @Override
        public TextFormatter getFormatter() {
            return new CamelCaseFormatter();
        }
    },
    BINARY {
        @Override
        public TextFormatter getFormatter() {
            return new BinaryFormatter();
        }
    };

    /**
     * Get formatter
     * @return a type of format
     */
    public abstract TextFormatter getFormatter();
}
