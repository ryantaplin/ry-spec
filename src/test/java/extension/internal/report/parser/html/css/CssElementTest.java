package extension.internal.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CssElementTest {

    @Test
    void getAttributeWithValue() {
        CssElement cssElement = new TestCssElement("attribute", "blue");
        assertThat(cssElement.getAttributeWithValue()).isEqualTo("attribute:blue");
    }

    @Test
    void cssElementsAreEqualWhenContentMatches() {
        assertThat(new TestCssElement("attribute", "blue"))
                .isEqualTo(new TestCssElement("attribute", "blue"));
    }

    @Test
    void cssElementsAreNotEqualWhenAttributeIsDifferent() {
        assertThat(new TestCssElement("attribute", "blue"))
                .isNotEqualTo(new TestCssElement("background-attribute", "blue"));
    }

    @Test
    void cssElementsAreNotEqualWhenValueIsDifferent() {
        assertThat(new TestCssElement("attribute", "blue"))
                .isNotEqualTo(new TestCssElement("attribute", "orange"));
    }

    public class TestCssElement extends CssElement {

        public TestCssElement(String attribute, String value) {
            super(attribute, value);
        }
    }
}