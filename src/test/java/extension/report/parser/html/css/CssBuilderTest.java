package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.report.parser.html.css.CssBuilder.cssBuilder;
import static extension.report.parser.html.css.CssColour.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class CssBuilderTest {

    private static final String WHITE_HEX = "#FFFFFF";

    @Test
    void multiStyleResultAsString() {
        CssBuilder cssBuilder = cssBuilder()
                .withFontColour(WHITE)
                .withBackgroundColour(WHITE);
        assertThat(cssBuilder.toString()).isEqualTo(String.format("color:%s; background-color:%s", WHITE_HEX, WHITE_HEX));
    }

    @Test
    void backgroundColourAsString() {
        CssBuilder cssBuilder = cssBuilder().withBackgroundColour(WHITE);
        assertThat(cssBuilder.toString()).isEqualTo(String.format("background-color:%s", WHITE_HEX));
    }

    @Test
    void fontColourAsString() {
        CssBuilder cssBuilder = cssBuilder().withFontColour(WHITE);
        assertThat(cssBuilder.toString()).isEqualTo(String.format("color:%s", WHITE_HEX));
    }
}