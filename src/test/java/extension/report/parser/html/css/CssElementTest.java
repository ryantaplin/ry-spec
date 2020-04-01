package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CssElementTest {

    @Test
    void getAttributeWithValue() {
        CssElement cssElement = new CssElement("color", "blue");
        assertThat(cssElement.getAttributeWithValue()).isEqualTo("color:blue");
    }
}