package extension.internal.report.parser.html.css.attribute;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.attribute.CssBackgroundColour.backgroundColour;
import static extension.internal.report.parser.html.css.attribute.value.CssColour.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class CssBackgroundColourTest {

    @Test
    void backgroundColourAsString() {
        String result = backgroundColour(WHITE).getAttributeWithValue();
        assertThat(result).isEqualTo("background-color:#FFFFFF");
    }
}