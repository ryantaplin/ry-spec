package extension.internal.report.parser.html.css.helper;

import extension.internal.report.parser.html.css.CssElements;
import extension.internal.report.parser.html.css.CssColour;
import extension.internal.report.parser.html.css.CssPosition;
import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.CssBackgroundColour.backgroundColour;
import static extension.internal.report.parser.html.css.CssBorder.border;
import static extension.internal.report.parser.html.css.CssMargin.margin;
import static extension.internal.report.parser.html.css.CssPadding.padding;
import static helpers.assertions.CssAssertions.assertThatCss;

class TestContentCssHelperTest {

    private final TestContentCssHelper helper = new TestContentCssHelper();

    @Test
    void headerCssContainsExpectedAttributes() {
        CssElements cssElements = helper.headerCss();
        assertThatCss(cssElements).containsExactlyInAnyOrder(
                backgroundColour(CssColour.BLUE),
                border(1, CssPosition.BOTTOM),
                padding(1, 1, 1, 1)
        );
    }

    @Test
    void sourceCodeCssContainsExpectedAttributes() {
        CssElements cssElements = helper.sourceCodeCss();
        assertThatCss(cssElements)
                .containsExactlyInAnyOrder(
                        border(1),
                        margin(10, 10, 0, 10),
                        padding(5, 5, 5, 5)
                );
    }

    @Test
    void containerCssContainsExpectedAttributes() {
        CssElements cssElements = helper.containerCss();
        assertThatCss(cssElements).containsExactlyInAnyOrder(
                margin(5, 5, 5, 5),
                border(1)
        );
    }

    @Test
    void capturedInterestingElementCssContainsExpectedAttributes() {
        CssElements cssElements = helper.capturedInterestingElementCss();
        assertThatCss(cssElements).containsExactlyInAnyOrder(
                border(1),
                margin(0, 10, 10, 10),
                padding(2, 2, 2, 2)
        );
    }

    @Test
    void emptyDivPaddingContainsExpectedAttributes() {
        CssElements cssElements = helper.emptyDivPadding();
        assertThatCss(cssElements).containsExactlyInAnyOrder(
                margin(5, 2, 10, 2)
        );
    }
}