package extension.report.parser.html.css;

public class CssMargin extends CssElement {

    private CssMargin(String attribute, String value) {
        super(attribute, value);
    }

    public static CssMargin margin(int top, int right, int bottom, int left) {
        String formattedValue = String.format("%spx %spx %spx %spx", top, right, bottom, left);
        return new CssMargin("margin", formattedValue);
    }
}
