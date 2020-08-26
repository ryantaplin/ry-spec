package extension.internal.report.parser.html.css.attribute;

public enum CssFontWeightType {
    BOLD("bold");

    private String value;


    CssFontWeightType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}