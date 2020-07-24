package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.report.parser.html.css.CssDisplay.display;
import static extension.report.parser.html.css.CssDisplayType.NONE;
import static org.assertj.core.api.Assertions.assertThat;

class CssDisplayTest {

    @Test
    void displayAsString() {
        assertThat(display(NONE).getAttributeWithValue())
                .isEqualTo("display:none");
    }
}