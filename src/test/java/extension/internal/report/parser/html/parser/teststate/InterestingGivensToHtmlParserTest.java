package extension.internal.report.parser.html.parser.teststate;

import extension.internal.report.parser.html.css.helper.TestContentCssHelper;
import extension.internal.report.parser.html.element.DivElement;
import extension.internal.report.parser.html.element.HtmlElement;
import extension.defaults.DefaultTestState;
import extension.TestState;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;
import static helpers.assertions.HtmlAssertions.assertThatHtml;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InterestingGivensToHtmlParserTest {

    private final TestContentCssHelper cssHelper = mock(TestContentCssHelper.class);
    private final InterestingGivensToHtmlParser parser = new InterestingGivensToHtmlParser(cssHelper);

    private final TestState state = mock(TestState.class);

    @Test
    void returnsDivElementWithInterestingKeyAndValue() {
        when(state.getInterestingGivenEntries()).thenReturn(List.of(entry("key", List.of("value"))));
        HtmlElement result = parser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("Interesting Givens")),
                        div(entryWithKeyAndValues("key: ", "value"))
                ));
    }

    @Test
    void returnsDivElementWithAKeyAndMultipleValues() {
        when(state.getInterestingGivenEntries()).thenReturn(List.of(entry("key", List.of("value", "anotherValue"))));
        HtmlElement result = parser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("Interesting Givens")),
                        div(entryWithKeyAndValues("key: ", "value", "anotherValue"))
                )
        );
    }

    @Test
    void returnsDivElementWithMultipleKeysAndValues() {
        when(state.getInterestingGivenEntries()).thenReturn(List.of(
                entry("key0", List.of("k0Avalue", "k0Bvalue")),
                entry("key1", List.of("k1Avalue", "k1Bvalue"))
        ));
        HtmlElement result = parser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("Interesting Givens")),
                        div(
                                entryWithKeyAndValues("key0: ", "k0Avalue", "k0Bvalue"),
                                entryWithKeyAndValues("key1: ", "k1Avalue", "k1Bvalue")
                        )
                ));
    }

    @Test
    void returnsNullWhenStateIsNull() {
        HtmlElement result = parser.parse(null);
        assertThat(result).isNull();
    }

    @Test
    void returnsNullWhenStateHasNoInterestingGivens() {
        HtmlElement result = parser.parse(new DefaultTestState());
        assertThat(result).isNull();
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