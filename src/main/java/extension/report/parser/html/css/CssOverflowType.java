package extension.report.parser.html.css;

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