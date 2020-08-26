package extension.internal.report.parser.html.css.attribute;

public enum CssBorderCollapseType {
    COLLAPSE("collapse");

    private String value;

    CssBorderCollapseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}