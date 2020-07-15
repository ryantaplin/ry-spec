package extension.report.parser.html.css;

public class CssFontColour extends CssElement {

    private CssFontColour(String attribute, String value) {
        super(attribute, value);
    }

    public static CssFontColour fontColour(CssColour colour) {
        return new CssFontColour("color", colour.getHexValue());
    }
}
