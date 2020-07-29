package extension.internal.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CssDisplayTypeTest {

    @Test
    void displayTypeTest() {
        assertThat(CssDisplayType.NONE.getValue()).isEqualTo("none");
    }
}