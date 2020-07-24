package extension.report.parser.html.css;

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