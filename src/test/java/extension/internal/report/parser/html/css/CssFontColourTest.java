package extension.internal.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.CssElements.css;
import static extension.internal.report.parser.html.css.CssColour.WHITE;
import static extension.internal.report.parser.html.css.CssFontColour.fontColour;
import static org.assertj.core.api.Assertions.assertThat;

class CssFontColourTest {

    @Test
    void fontColourAsString() {
        CssElements result = css(fontColour(WHITE));
        assertThat(result.asString()).isEqualTo(String.format("style=\"color:%s\"", "#FFFFFF"));
    }
}