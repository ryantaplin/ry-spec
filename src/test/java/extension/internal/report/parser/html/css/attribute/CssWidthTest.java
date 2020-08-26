package extension.internal.report.parser.html.css.attribute;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.attribute.CssFontWeight.fontWeight;
import static extension.internal.report.parser.html.css.attribute.CssFontWeightType.BOLD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CssWidthTest {

    @Test
    void widthAsString() {
        String result = CssWidth.width("10").getAttributeWithValue();
        assertThat(result).isEqualTo("width:10");
    }
}