package extension.internal.report.parser.html.css.attribute;

public class CssPadding extends CssElement {

    private CssPadding(String attribute, String value) {
        super(attribute, value);
    }

    public static CssPadding padding(int top, int right, int bottom, int left) {
        String formattedValue = String.format("%spx %spx %spx %spx", top, right, bottom, left);
        return new CssPadding("padding", formattedValue);
    }
}
