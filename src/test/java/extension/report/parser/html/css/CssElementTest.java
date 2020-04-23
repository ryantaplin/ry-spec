package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CssElementTest {

    @Test
    void getAttributeWithValue() {
        CssElement cssElement = new CssElement("color", "blue");
        assertThat(cssElement.getAttributeWithValue()).isEqualTo("color:blue");
    }

    @Test
    void cssElementsAreEqualWhenContentMatches() {
        assertThat(new CssElement("color", "blue"))
                .isEqualTo(new CssElement("color", "blue"));
    }

    @Test
    void cssElementsAreNotEqualWhenAttributeIsDifferent() {
        assertThat(new CssElement("color", "blue"))
                .isNotEqualTo(new CssElement("background-color", "blue"));
    }

    @Test
    void cssElementsAreNotEqualWhenValueIsDifferent() {
        assertThat(new CssElement("color", "blue"))
                .isNotEqualTo(new CssElement("color", "orange"));
    }
}