package extension.report.parser;

import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.report.parser.html.element.DivElement;
import extension.report.parser.html.element.HtmlElement;
import extension.report.parser.html.parser.TestStateToHtmlParser;
import extension.test.state.TestState;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;
import static helpers.assertions.HtmlAssertions.assertThatHtml;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestStateToHtmlParserTest {

    private final TestContentCssHelper cssHelper = mock(TestContentCssHelper.class);
    private final TestStateToHtmlParser testStateToHtmlParser = new TestStateToHtmlParser(cssHelper);

    private final TestState state = mock(TestState.class);

    @Test
    void returnsDivElementWithInterestingKeyAndValue() {
        when(state.getInterestingEntryList()).thenReturn(List.of(entry("key", List.of("value"))));
        HtmlElement result = testStateToHtmlParser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("Interesting Givens")),
                        div(entryWithKeyAndValues("key", "value"))
                ));
    }

    @Test
    void returnsDivElementWithAKeyAndMultipleValues() {
        when(state.getInterestingEntryList()).thenReturn(List.of(entry("key", List.of("value", "anotherValue"))));
        HtmlElement result = testStateToHtmlParser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("Interesting Givens")),
                        div(entryWithKeyAndValues("key", "value", "anotherValue"))
                ));
    }

    @Test
    void returnsDivElementWithMultipleKeysAndValues() {
        when(state.getInterestingEntryList()).thenReturn(List.of(
                entry("key0", List.of("k0Avalue", "k0Bvalue")),
                entry("key1", List.of("k1Avalue", "k1Bvalue"))
        ));
        HtmlElement result = testStateToHtmlParser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("Interesting Givens")),
                        div(
                                entryWithKeyAndValues("key0", "k0Avalue", "k0Bvalue"),
                                entryWithKeyAndValues("key1", "k1Avalue", "k1Bvalue")
                        )
                ));
    }

    @Test
    void returnsEmptyDivWhenNullStateIsProvided() {
        HtmlElement result = testStateToHtmlParser.parse(null);
        assertThatHtml(result).isEqualTo(div());
    }

    private DivElement entryWithKeyAndValues(String key, String... values) {
        return div(content(key), content(String.join(", ", values)));
    }

    private static Map.Entry<String, List<Object>> entry(String key, List<Object> value) {
        return new Map.Entry<>() {
            @Override
            public String getKey() {
                return key;
            }

            @Override
            public List<Object> getValue() {
                return value;
            }

            @Override
            public List<Object> setValue(List<Object> o) {
                return null;
            }
        };
    }
}