package extension.internal.report.parser.html.css;

public class CssBackgroundColour extends CssElement {

    private CssBackgroundColour(String attribute, String value) {
        super(attribute, value);
    }

    public static CssBackgroundColour backgroundColour(CssColour colour) {
        return new CssBackgroundColour("background-color", colour.getHexValue());
    }
}
