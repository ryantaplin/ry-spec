package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.report.parser.html.css.CssElements.css;
import static extension.report.parser.html.css.CssPadding.padding;
import static org.assertj.core.api.Assertions.assertThat;

class CssPaddingTest {

    @Test
    void paddingAsString() {
        CssElements result = css(padding(10, 10, 10, 10));
        assertThat(result.asString()).isEqualTo("style=\"padding:10px 10px 10px 10px\"");
    }
}