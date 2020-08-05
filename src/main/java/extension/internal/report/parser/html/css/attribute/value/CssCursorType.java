package extension.internal.report.parser.html.css.attribute.value;

public enum CssCursorType {
    POINTER("pointer");

    private String value;


    CssCursorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}