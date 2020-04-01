package extension.report.parser.html.css;

class CssElement {

    private String attribute;
    private String value;

    public CssElement(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public String getAttributeWithValue() {
        return String.format("%s:%s", attribute, value);
    }
}

