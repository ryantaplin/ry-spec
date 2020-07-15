package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.report.parser.html.css.CssElements.css;
import static extension.report.parser.html.css.CssFontSize.fontSize;
import static org.assertj.core.api.Assertions.assertThat;

class CssFontSizeTest {

    @Test
    void fontSizeAsString() {
        CssElements result = css(fontSize(50));
        assertThat(result.asString()).isEqualTo("style=\"font-size:50px\"");
    }
}