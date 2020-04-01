package extension.report.parser.html.element;

import org.junit.jupiter.api.Test;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;
import static org.assertj.core.api.Assertions.assertThat;

class DivElementTest {

    @Test
    void asStringResultReturnsRawContentWrappedInDiv() {
        assertThat(div(content("one")).asString()).isEqualTo("<div>one</div>");
    }

    @Test
    void asStringResultReturnsMultipleHtmlInputsWrappedInput() {
        DivElement div = div(div(content("one")), content("two"));
        assertThat(div.asString()).isEqualTo("<div><div>one</div>two</div>");
    }
}