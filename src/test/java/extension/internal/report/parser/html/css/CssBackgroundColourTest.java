package extension.internal.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.CssBackgroundColour.backgroundColour;
import static extension.internal.report.parser.html.css.CssElements.css;
import static extension.internal.report.parser.html.css.CssColour.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class CssBackgroundColourTest {

    @Test
    void backgroundColourAsString() {
        CssElements result = css(backgroundColour(WHITE));
        assertThat(result.asString()).isEqualTo(String.format("style=\"background-color:%s\"", "#FFFFFF"));
    }
}