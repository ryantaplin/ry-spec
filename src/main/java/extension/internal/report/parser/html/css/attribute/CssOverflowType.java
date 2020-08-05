package extension.internal.report.parser.html.css.attribute;

public enum CssOverflowType {
    HIDDEN("hidden");

    private String value;


    CssOverflowType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}