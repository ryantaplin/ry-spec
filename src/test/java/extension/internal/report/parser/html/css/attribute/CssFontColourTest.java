package extension.internal.report.parser.html.css.attribute;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.attribute.value.CssColour.WHITE;
import static extension.internal.report.parser.html.css.attribute.CssFontColour.fontColour;
import static org.assertj.core.api.Assertions.assertThat;

class CssFontColourTest {

    @Test
    void fontColourAsString() {
        String result = fontColour(WHITE).getAttributeWithValue();
        assertThat(result).isEqualTo("color:#FFFFFF");
    }
}