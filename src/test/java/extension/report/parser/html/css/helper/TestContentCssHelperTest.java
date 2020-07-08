package extension.report.parser.html.css.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TestContentCssHelperTest {

    private final TestContentCssHelper helper = new TestContentCssHelper();

    @ParameterizedTest(name = "Expecting header to contain attribute \"{0}\"")
    @MethodSource("headerAttributeValues")
    void headerCssContainsExpectedAttributes(String attributeValue) {
        String cssBuilder = helper.headerCss().asString();

        assertThat(cssBuilder).contains(attributeValue);
    }

    @Test
    void bodyCssContainsExpectedAttributes() {
        String cssBuilder = helper.bodyCss().asString();
        assertThat(cssBuilder).contains("padding:2px 2px 2px 2px");
    }

    @ParameterizedTest(name = "Expecting css container to contain attribute \"{0}\"")
    @MethodSource("cssAttributeValues")
    void containerCssContainsExpectedAttributes(String attributeValue) {
        String cssBuilder = helper.containerCss().asString();
        assertThat(cssBuilder).contains(attributeValue);
    }

    private static Stream<Arguments> cssAttributeValues() {
        return Stream.of(
                Arguments.of("margin:5px 5px 5px 5px"),
                Arguments.of("border:1px solid black")
        );
    }

    private static Stream<Arguments> headerAttributeValues() {
        return Stream.of(
                Arguments.of("background-color:#70e1e1"),
                Arguments.of("border-bottom:1px solid black"),
                Arguments.of("padding:1px 1px 1px 1px")
        );
    }

}