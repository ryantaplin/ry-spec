package extension.report.parser.helper;

import static com.google.common.base.Ascii.toUpperCase;

public class SentenceFormatter {

    public String format(String value) {
        if (value.isEmpty()) return "";
        return toUpperCase(value.charAt(0)) + value.substring(1).toLowerCase();
    }

}
