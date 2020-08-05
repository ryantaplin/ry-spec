package extension.internal.report.parser.html.css.attribute;

import extension.internal.report.parser.html.css.attribute.value.CssDisplayType;

public class CssDisplay extends CssElement {

    private CssDisplay(String attribute, String value) {
        super(attribute, value);
    }

    public static CssDisplay display(CssDisplayType displayType) {
        return new CssDisplay("display", displayType.getValue());
    }
}
