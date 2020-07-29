package extension.internal.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.CssBackgroundColour.backgroundColour;
import static extension.internal.report.parser.html.css.CssColour.BLUE;
import static extension.internal.report.parser.html.css.CssColour.WHITE;
import static extension.internal.report.parser.html.css.CssElements.css;
import static extension.internal.report.parser.html.css.CssFontColour.fontColour;
import static extension.internal.report.parser.html.css.CssFontSize.fontSize;
import static org.assertj.core.api.Assertions.assertThat;

class CssElementsTest {

    @Test
    void singleCssElementAsString() {
        CssElements result = css(fontColour(WHITE));
        assertThat(result.asString()).isEqualTo("style=\"color:#FFFFFF\"");
    }

    @Test
    void multiCssElementAsStringHasCommaSeparatedElements() {
        CssElements result = css(
                fontColour(WHITE),
                backgroundColour(WHITE)
        );
        assertThat(result.asString()).isEqualTo("style=\"color:#FFFFFF; background-color:#FFFFFF\"");
    }

    @Test
    void noCssElementsReturnsBlankString() {
        CssElements result = css();
        assertThat(result.asString()).isBlank();
    }

    @Test
    void cssElementsAreEqualWhenCssElementIsTheSame() {
        assertThat(css(fontColour(WHITE)))
                .isEqualTo(css(fontColour(WHITE)));
    }

    @Test
    void cssElementsAreNotEqualWhenCssElementIsDifferent() {
        assertThat(css(fontColour(BLUE)))
                .isNotEqualTo(css(fontColour(WHITE)));
    }

    @Test
    void cssElementsAreEqualWhenContainMultipleMatchingElements() {
        assertThat(css(fontColour(WHITE), backgroundColour(BLUE), fontSize(10)))
                .isEqualTo(css(fontColour(WHITE), backgroundColour(BLUE), fontSize(10)));
    }

    @Test
    void cssElementsAreNotEqualWhenOneOfManyElementsIsDifferent() {
        assertThat(css(fontColour(WHITE), backgroundColour(BLUE), fontSize(55)))
                .isNotEqualTo(css(fontColour(WHITE), backgroundColour(BLUE), fontSize(10)));
    }
}