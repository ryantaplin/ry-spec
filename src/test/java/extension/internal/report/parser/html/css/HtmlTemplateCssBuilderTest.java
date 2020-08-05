package extension.internal.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.CssClassBuilder.cssClass;
import static extension.internal.report.parser.html.css.HtmlTemplateCssBuilder.cssBuilder;
import static extension.internal.report.parser.html.css.attribute.CssFontSize.fontSize;
import static org.assertj.core.api.Assertions.assertThat;

class HtmlTemplateCssBuilderTest {

    @Test
    void emptyCssBuilderReturnsEmptyString() {
        String result = cssBuilder().build();
        assertThat(result).isEmpty();
    }

    @Test
    void returnedCssAttributeForSingleCssInput() {
        String result = cssBuilder()
                .addCss(cssClass("name").withAttribute(fontSize(1)))
                .build();
        assertThat(result).isEqualTo(".name { font-size:1px }");
    }

    @Test
    void returnedCssAttributeForMultipleCssInputIsSeparatedByLineBreak() {
        String result = cssBuilder()
                .addCss(cssClass("name").withAttribute(fontSize(1)))
                .addCss(cssClass("name2").withAttribute(fontSize(2)))
                .build();
        assertThat(result).isEqualTo(".name { font-size:1px }\n.name2 { font-size:2px }");
    }
}