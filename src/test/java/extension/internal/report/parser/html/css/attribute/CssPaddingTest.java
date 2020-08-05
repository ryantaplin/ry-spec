package extension.internal.report.parser.html.css.attribute;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.attribute.CssPadding.padding;
import static org.assertj.core.api.Assertions.assertThat;

class CssPaddingTest {

    @Test
    void paddingAsString() {
        String result = padding(10, 10, 10, 10).getAttributeWithValue();
        assertThat(result).isEqualTo("padding:10px 10px 10px 10px");
    }
}