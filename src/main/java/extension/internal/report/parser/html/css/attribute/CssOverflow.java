package extension.internal.report.parser.html.css.attribute;

public class CssOverflow extends CssElement {

    private CssOverflow(String attribute, String value) {
        super(attribute, value);
    }

    public static CssOverflow overflow(CssOverflowType overflowType) {
        return new CssOverflow("overflow", overflowType.getValue());
    }
}
