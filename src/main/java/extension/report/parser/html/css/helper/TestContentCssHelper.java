package extension.report.parser.html.css.helper;

import extension.report.parser.html.css.*;

import static extension.report.parser.html.css.CssBackgroundColour.backgroundColour;
import static extension.report.parser.html.css.CssBorder.border;
import static extension.report.parser.html.css.CssColour.*;
import static extension.report.parser.html.css.CssCursorType.*;
import static extension.report.parser.html.css.CssDisplay.display;
import static extension.report.parser.html.css.CssDisplayType.*;
import static extension.report.parser.html.css.CssElements.css;
import static extension.report.parser.html.css.CssFontColour.fontColour;
import static extension.report.parser.html.css.CssFontSize.fontSize;
import static extension.report.parser.html.css.CssHoverCursor.hoverCursor;
import static extension.report.parser.html.css.CssMargin.margin;
import static extension.report.parser.html.css.CssOverflow.overflow;
import static extension.report.parser.html.css.CssOverflowType.HIDDEN;
import static extension.report.parser.html.css.CssPadding.padding;
import static extension.report.parser.html.css.CssPosition.*;
import static extension.report.parser.html.css.CssUserSelectDisabled.*;

//TODO: convert this to a factory and split out cssHelpers per element (i.e header, sourceCode, testState)..
public class TestContentCssHelper {

    public CssElements headerCss() {
        return css(
                backgroundColour(BLUE),
                border(1, BOTTOM),
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

    public CssElements collapsibleButton() {
        return css(
                backgroundColour(BLUE),
                fontColour(BLACK),
                hoverCursor(POINTER),
                padding(5, 5, 5, 5),
                border(1),
                fontSize(15),
                webKitUserSelectDisabled(),
                mozUserSelectDisabled(),
                msUserSelectDisabled()
        );
    }

    public CssElements collapsibleContent() {
        return css(
                padding(0, 18, 0 , 18),
                display(NONE),
                overflow(HIDDEN),
                border(1),
                backgroundColour(GREY)
        );
    }
}
