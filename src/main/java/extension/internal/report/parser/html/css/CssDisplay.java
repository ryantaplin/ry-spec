package extension.internal.report.parser.html.css;

public class CssDisplay extends CssElement {

    private CssDisplay(String attribute, String value) {
        super(attribute, value);
    }

    public static CssDisplay display(CssDisplayType displayType) {
        return new CssDisplay("display", displayType.getValue());
    }
}
