package extension.internal.report.parser.html.css.attribute;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.attribute.CssDisplay.display;
import static extension.internal.report.parser.html.css.attribute.value.CssDisplayType.NONE;
import static org.assertj.core.api.Assertions.assertThat;

class CssDisplayTest {

    @Test
    void displayAsString() {
        assertThat(display(NONE).getAttributeWithValue())
                .isEqualTo("display:none");
    }
}