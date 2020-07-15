package extension.report.parser.html.css;

import java.util.Objects;

public abstract class CssElement {

    private String attribute;
    private String value;

    public CssElement(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public String getAttributeWithValue() {
        return String.format("%s:%s", attribute, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CssElement that = (CssElement) o;
        return Objects.equals(attribute, that.attribute) &&
                Objects.equals(value, that.value);
    }
}

