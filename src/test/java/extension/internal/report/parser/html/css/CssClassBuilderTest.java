package extension.internal.report.parser.html.css;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.CssClassBuilder.cssClass;
import static extension.internal.report.parser.html.css.CssClassBuilder.cssClasses;
import static org.assertj.core.api.Assertions.assertThat;

class CssClassBuilderTest {

    @Test
    void cssClassReturnsCssClassAttributesBuilderContainingName() {
        assertThat(cssClass("name"))
                .isInstanceOf(CssClassWithAttributesBuilder.class)
                .extracting(CssClassWithAttributesBuilder::build).asString()
                .contains(".name");
    }

    @Test
    void cssClassesReturnsCssClassAttributesBuilderContainingAllNames() {
        assertThat(cssClasses("name", "name2"))
                .isInstanceOf(CssClassWithAttributesBuilder.class)
                .extracting(CssClassWithAttributesBuilder::build).asString()
                .contains(".name, .name2");
    }
}