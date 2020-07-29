package extension.internal.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.CssCursorType.POINTER;
import static extension.internal.report.parser.html.css.CssHoverCursor.hoverCursor;
import static org.assertj.core.api.Assertions.assertThat;

class CssHoverCursorTest {

    @Test
    void hoverCursorAsString() {
        assertThat(hoverCursor(POINTER).getAttributeWithValue())
                .isEqualTo("cursor:pointer");
    }

}