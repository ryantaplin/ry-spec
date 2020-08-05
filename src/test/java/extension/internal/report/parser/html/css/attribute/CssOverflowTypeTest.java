package extension.internal.report.parser.html.css.attribute;

import extension.internal.report.parser.html.css.attribute.CssOverflowType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CssOverflowTypeTest {

    @Test
    void overflowTypeValueTest() {
        assertThat(CssOverflowType.HIDDEN.getValue()).isEqualTo("hidden");
    }
}