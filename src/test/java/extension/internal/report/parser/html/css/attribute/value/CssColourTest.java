package extension.internal.report.parser.html.css.attribute.value;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.attribute.value.CssColour.BLACK;
import static extension.internal.report.parser.html.css.attribute.value.CssColour.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class CssColourTest {

    @Test
    void blackHexValue() {
        assertThat(BLACK.getHexValue()).isEqualTo("#444444");
    }

    @Test
    void whiteHexValue() {
        assertThat(WHITE.getHexValue()).isEqualTo("#FFFFFF");
    }
}