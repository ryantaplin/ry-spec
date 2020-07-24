package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.report.parser.html.css.CssDisplay.display;
import static extension.report.parser.html.css.CssDisplayType.NONE;
import static extension.report.parser.html.css.CssOverflow.overflow;
import static extension.report.parser.html.css.CssOverflowType.HIDDEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CssOverflowTest {

    @Test
    void overflowAsString() {
        assertThat(overflow(HIDDEN).getAttributeWithValue())
                .isEqualTo("overflow:hidden");
    }

}