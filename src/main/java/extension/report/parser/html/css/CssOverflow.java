package extension.report.parser.html.css;

public class CssOverflow extends CssElement {

    private CssOverflow(String attribute, String value) {
        super(attribute, value);
    }

    public static CssOverflow overflow(CssOverflowType overflowType) {
        return new CssOverflow("overflow", overflowType.getValue());
    }
}
