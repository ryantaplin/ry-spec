package extension.internal.report.parser.html.css;

import org.junit.jupiter.api.Test;

import java.util.List;

import static extension.internal.report.parser.html.css.attribute.CssBorder.border;
import static extension.internal.report.parser.html.css.attribute.CssFontSize.fontSize;
import static org.assertj.core.api.Assertions.assertThat;

class CssClassWithAttributesBuilderTest {

    @Test
    void namesArePrefixedWithDotAndSeparatedByComma() {
        String result = new CssClassWithAttributesBuilder(List.of("name", "name2", "name3")).build();
        assertThat(result).contains(".name, .name2, .name3");
    }

    @Test
    void attributesAreEnclosedByCurlyBraces() {
        String result = new CssClassWithAttributesBuilder(List.of("name"))
                .withAttribute(border(1))
                .build();
        assertThat(result).contains("{ border:1px solid black }");
    }

    @Test
    void multipleAttributesAreSeparatedBySemiColon() {
        String result = new CssClassWithAttributesBuilder(List.of("name"))
                .withAttribute(border(1))
                .withAttribute(fontSize(5))
                .build();
        assertThat(result).contains("{ border:1px solid black; font-size:5px }");
    }
}