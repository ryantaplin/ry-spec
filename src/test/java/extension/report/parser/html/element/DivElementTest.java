package extension.report.parser.html.element;

import org.junit.jupiter.api.Test;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.css.CssElements.css;
import static extension.report.parser.html.css.CssFontSize.fontSize;
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

    @Test
    void emptyDivElementsAreEqual() {
        assertThat(div()).isEqualTo(div());
    }

    @Test
    void divElementsAreEqualWhenContainSameElementContent() {
        assertThat(div(content("someContent")))
                .isEqualTo(div(content("someContent")));
    }

    @Test
    void divElementsAreNotEqualWhenContainDifferentElementContent() {
        assertThat(div(content("someContent")))
                .isNotEqualTo(div(content("differentContent")));
    }

    @Test
    void divElementsAreEqualWhenContainSameCssBuilderContent() {
        assertThat(div().with(css(fontSize(10))))
                .isEqualTo(div().with(css(fontSize(10))));
    }

    @Test
    void divElementsAreNotEqualWhenContainDifferentCssBuilderContent() {
        assertThat(div().with(css(fontSize(10))))
                .isNotEqualTo(div().with(css(fontSize(99))));
    }
}