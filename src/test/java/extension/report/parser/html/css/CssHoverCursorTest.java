package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.report.parser.html.css.CssCursorType.POINTER;
import static extension.report.parser.html.css.CssDisplay.display;
import static extension.report.parser.html.css.CssDisplayType.NONE;
import static extension.report.parser.html.css.CssHoverCursor.hoverCursor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CssHoverCursorTest {

    @Test
    void hoverCursorAsString() {
        assertThat(hoverCursor(POINTER).getAttributeWithValue())
                .isEqualTo("cursor:pointer");
    }

}