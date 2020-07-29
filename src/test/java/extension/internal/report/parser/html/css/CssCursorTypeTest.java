package extension.internal.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CssCursorTypeTest {

    @Test
    void pointerValueTest() {
        assertThat(CssCursorType.POINTER.getValue()).isEqualTo("pointer");
    }
}