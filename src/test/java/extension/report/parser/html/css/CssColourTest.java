package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.report.parser.html.css.CssColour.BLACK;
import static extension.report.parser.html.css.CssColour.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class CssColourTest {

    @Test
    void blackHexValue() {
        assertThat(BLACK.getHexValue()).isEqualTo("#000000");
    }

    @Test
    void whiteHexValue() {
        assertThat(WHITE.getHexValue()).isEqualTo("#FFFFFF");
    }
}