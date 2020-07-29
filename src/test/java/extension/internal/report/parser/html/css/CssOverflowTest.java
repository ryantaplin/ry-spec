package extension.internal.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.CssOverflow.overflow;
import static extension.internal.report.parser.html.css.CssOverflowType.HIDDEN;
import static org.assertj.core.api.Assertions.assertThat;

class CssOverflowTest {

    @Test
    void overflowAsString() {
        assertThat(overflow(HIDDEN).getAttributeWithValue())
                .isEqualTo("overflow:hidden");
    }

}