package extension.internal.report.parser.html.css.attribute;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.attribute.CssMargin.margin;
import static org.assertj.core.api.Assertions.assertThat;

class CssMarginTest {

    @Test
    void marginAsString() {
        String result = margin(10, 10, 10, 10).getAttributeWithValue();
        assertThat(result).isEqualTo("margin:10px 10px 10px 10px");
    }
}