package extension.internal.report.parser.html.css.attribute.value;

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