package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.report.parser.html.css.CssBuilder.css;
import static extension.report.parser.html.css.CssColour.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class CssBuilderTest {

    private static final String WHITE_HEX = "#FFFFFF";

    @Test
    void multiStyleResultAsString() {
        CssBuilder cssBuilder = css()
                .fontColour(WHITE)
                .backgroundColour(WHITE);
        assertThat(cssBuilder.asString()).isEqualTo(String.format("style=\"color:%s; background-color:%s\"", WHITE_HEX, WHITE_HEX));
    }

    @Test
    void backgroundColourAsString() {
        CssBuilder cssBuilder = css().backgroundColour(WHITE);
        assertThat(cssBuilder.asString()).isEqualTo(String.format("style=\"background-color:%s\"", WHITE_HEX));
    }

    @Test
    void fontColourAsString() {
        CssBuilder cssBuilder = css().fontColour(WHITE);
        assertThat(cssBuilder.asString()).isEqualTo(String.format("style=\"color:%s\"", WHITE_HEX));
    }
}