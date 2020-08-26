package extension.internal.report.parser.html.css.attribute;

public class CssWidth extends CssElement {

    private CssWidth(String attribute, String value) {
        super(attribute, value);
    }

    public static CssWidth width(String value) {
        return new CssWidth("width", value);
    }
}
