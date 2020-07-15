package extension.report.parser.html.css.helper;

import extension.report.parser.html.css.CssElements;
import extension.report.parser.html.css.CssColour;
import extension.report.parser.html.css.CssPosition;

import static extension.report.parser.html.css.CssBackgroundColour.backgroundColour;
import static extension.report.parser.html.css.CssBorder.border;
import static extension.report.parser.html.css.CssElements.css;
import static extension.report.parser.html.css.CssMargin.margin;
import static extension.report.parser.html.css.CssPadding.padding;

//TODO: convert this to a factory and split out cssHelpers per element (i.e header, sourceCode, testState)..
public class TestContentCssHelper {

    public CssElements headerCss() {
        return css(
                backgroundColour(CssColour.BLUE),
                border(1, CssPosition.BOTTOM),
                padding(1, 1, 1, 1)
        );
    }

    public CssElements sourceCodeCss() {
        return css(
                border(1),
                margin(10, 10, 0, 10),
                padding(5, 5, 5, 5)
        );
    }

    public CssElements capturedInterestingElementCss() {
        return css(
                border(1), //temp - for visibility
                margin(0, 10, 10, 10),
                padding(2, 2, 2, 2)
        );
    }

    public CssElements containerCss() {
        return css(
                margin(5, 5, 5, 5),
                border(1)
        );
    }

    public CssElements emptyDivPadding() {
        return css(margin(5, 2, 10, 2));
    }
}
