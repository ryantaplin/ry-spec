package extension.report.parser.html.css;

public class CssFontSize extends CssElement {

    private CssFontSize(String attribute, String value) {
        super(attribute, value);
    }

    public static CssFontSize fontSize(int size) {
        String formattedValue = String.format("%spx", size);
        return new CssFontSize("font-size", formattedValue);
    }
}
