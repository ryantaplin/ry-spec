package extension.internal.report.parser.html.css.attribute;

import extension.internal.report.parser.html.css.attribute.value.CssColour;

public class CssFontColour extends CssElement {

    private CssFontColour(String attribute, String value) {
        super(attribute, value);
    }

    public static CssFontColour fontColour(CssColour colour) {
        return new CssFontColour("color", colour.getHexValue());
    }
}
