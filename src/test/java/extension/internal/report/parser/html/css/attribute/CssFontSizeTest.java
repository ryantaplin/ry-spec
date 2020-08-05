package extension.internal.report.parser.html.css.attribute;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.attribute.CssFontSize.fontSize;
import static org.assertj.core.api.Assertions.assertThat;

class CssFontSizeTest {

    @Test
    void fontSizeAsString() {
        String result = fontSize(50).getAttributeWithValue();
        assertThat(result).isEqualTo("font-size:50px");
    }
}