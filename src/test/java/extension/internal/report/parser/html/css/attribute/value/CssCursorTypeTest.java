package extension.internal.report.parser.html.css.attribute.value;

import extension.internal.report.parser.html.css.attribute.value.CssCursorType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CssCursorTypeTest {

    @Test
    void pointerValueTest() {
        assertThat(CssCursorType.POINTER.getValue()).isEqualTo("pointer");
    }
}