package extension.internal.report.parser.html.css.attribute;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.attribute.CssFontWeight.fontWeight;
import static extension.internal.report.parser.html.css.attribute.CssFontWeightType.BOLD;
import static org.assertj.core.api.Assertions.assertThat;

class CssFontWeightTest {

    @Test
    void fontWeightAsString() {
        String result = fontWeight(BOLD).getAttributeWithValue();
        assertThat(result).isEqualTo("font-weight:bold");
    }
}