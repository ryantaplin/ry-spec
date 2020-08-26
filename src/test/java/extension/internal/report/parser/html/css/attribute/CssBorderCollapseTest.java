package extension.internal.report.parser.html.css.attribute;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.attribute.CssBorderCollapse.borderCollapse;
import static extension.internal.report.parser.html.css.attribute.CssBorderCollapseType.COLLAPSE;
import static org.assertj.core.api.Assertions.assertThat;

class CssBorderCollapseTest {

    @Test
    void borderCollapseAsString() {
        String result = borderCollapse(COLLAPSE).getAttributeWithValue();
        assertThat(result).isEqualTo("border-collapse:collapse");
    }

}