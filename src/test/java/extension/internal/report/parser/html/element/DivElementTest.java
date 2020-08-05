package extension.internal.report.parser.html.element;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;
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
    void asStringResultDoesNotIncludeNullInputs() {
        DivElement div = div(null, div(null, content("one")), null, content("two"), null);
        assertThat(div.asString()).isEqualTo("<div><div>one</div>two</div>");
    }

    @Test
    void asStringWithClassNameReturnsClassAttribute() {
        DivElement div = div().withClassName("someClassName");
        assertThat(div.asString()).isEqualTo("<div class=\"someClassName\"></div>");
    }

    @Test
    void asStringWithOnClickFunctionReturnsOnClickAttribute() {
        DivElement div = div().withOnClickFunction("someFunc()");
        assertThat(div.asString()).isEqualTo("<div onClick=\"someFunc()\"></div>");
    }

    // TODO: this could be done better.. maybe a .withAttribute(AttributeType, AttributeValue) method
    //  where -> AttributeType.CLASS_NAME + AtrributeType.ON_CLICK
    @Test
    void asStringAttributesAreSpaceSeparatedWhenMultipleAttributesAreSet() {
        DivElement div = div().withClassName("someClassName").withOnClickFunction("someFunc()");
        assertThat(div.asString()).isEqualTo("<div class=\"someClassName\" onClick=\"someFunc()\"></div>");
    }

    @Test
    void isNotEmptyReturnsFalseWhenContentListIsEmpty() {
        assertThat(div().isNotEmpty()).isFalse();
    }

    @Test
    void isNotEmptyReturnsTrueWhenContentListIsEmpty() {
        assertThat(div(content("something")).isNotEmpty()).isTrue();
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
    void divElementsAreEqualWhenTheyHaveSameClassName() {
        assertThat(div().withClassName("someClassName"))
                .isEqualTo(div().withClassName("someClassName"));
    }

    @Test
    void divElementsAreNotEqualWhenTheyHaveDifferentClassName() {
        assertThat(div().withClassName("someClassName"))
                .isNotEqualTo(div().withClassName("differentClassName"));
    }

    @Test
    void divElementsAreEqualWhenTheyhaveSameOnClickFunctionValue() {
        assertThat(div().withOnClickFunction("someFunc()")).isEqualTo(div().withOnClickFunction("someFunc()"));
    }

    @Test
    void divElementsAreNotEqualWhenTheyHaveDifferentOnClickFunctionValue() {
        assertThat(div().withOnClickFunction("someFunc()")).isNotEqualTo(div().withOnClickFunction("diffFunc()"));
    }
}