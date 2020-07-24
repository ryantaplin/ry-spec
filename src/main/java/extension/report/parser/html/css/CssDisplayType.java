package extension.report.parser.html.css;

public enum CssDisplayType {
    NONE("none");

    private String value;


    CssDisplayType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}