package extension.internal.report.parser.html.css.attribute;

import java.util.Optional;

public class CssBorder extends CssElement {

    private CssBorder(String attribute, String value) {
        super(attribute, value);
    }

    public static CssBorder border(int width) {
        return border(width, null);
    }

    public static CssBorder border(int width, CssPosition position) {
        String formattedValue = String.format("%spx solid black", width);
        String formattedAttribute = attributePositionHelperFor("border", position);
        return new CssBorder(formattedAttribute, formattedValue);
    }

    private static String attributePositionHelperFor(String attribute, CssPosition location) {
        return Optional.ofNullable(location)
                .map(position -> position.toString().toLowerCase())
                .map(position -> String.format("%s-%s", attribute, position))
                .orElse(attribute);

    }

}
