package extension.report.parser.html;

import java.util.Objects;

public class HtmlContent implements HtmlValue {

    private final String value;

    private HtmlContent(String value) {
        this.value = value;
    }

    public static HtmlContent content(String value) {
        return new HtmlContent(value);
    }

    public String asString() {
        return value.replaceAll("\n", "<br>");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HtmlContent that = (HtmlContent) o;
        return Objects.equals(value, that.value);
    }
}