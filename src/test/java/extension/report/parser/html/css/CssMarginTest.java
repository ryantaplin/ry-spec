package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.report.parser.html.css.CssElements.css;
import static extension.report.parser.html.css.CssMargin.margin;
import static org.assertj.core.api.Assertions.assertThat;

class CssMarginTest {

    @Test
    void marginAsString() {
        CssElements result = css(margin(10, 10, 10, 10));
        assertThat(result.asString()).isEqualTo("style=\"margin:10px 10px 10px 10px\"");
    }
}