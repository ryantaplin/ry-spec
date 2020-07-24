package extension.report.parser.html.css;

public class CssHoverCursor extends CssElement {

    private CssHoverCursor(String attribute, String value) {
        super(attribute, value);
    }

    public static CssHoverCursor hoverCursor(CssCursorType cursor) {
        return new CssHoverCursor("cursor", cursor.getValue());
    }
}
