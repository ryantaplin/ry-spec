package extension.internal.report.parser.html.parser.teststate;

import extension.TestState;
import extension.defaults.DefaultTestState;
import extension.internal.report.parser.html.element.DivElement;
import extension.internal.report.parser.html.element.HtmlElement;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;
import static extension.internal.report.parser.html.element.TableElement.table;
import static extension.internal.report.parser.html.element.TableRow.row;
import static extension.internal.report.parser.html.element.TableRowData.rowData;
import static helpers.assertions.HtmlAssertions.assertThatHtml;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InterestingGivensToHtmlParserTest {

    private final InterestingGivensToHtmlParser parser = new InterestingGivensToHtmlParser();

    private final TestState state = mock(TestState.class);

    @Test
    void returnsDivElementWithInterestingKeyAndValue() {
        when(state.getInterestingGivenEntries()).thenReturn(List.of(entry("key", List.of("value"))));
        HtmlElement result = parser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("Interesting Givens")).withClassName("interestingGivensHeader"),
                        table(
                                row(
                                        rowData("key").withClassNames("tableEntity", "tableHeaderEntity"),
                                        rowData("value").withClassNames("tableEntity", "tableValueEntity")
                                ).withClassName("tableEntity")
                        ).withClassName("tableEntity")
                ).withClassName("interestingGivenContainer"));
    }

    @Test
    void returnsDivElementWithAKeyAndMultipleValues() {
        when(state.getInterestingGivenEntries()).thenReturn(List.of(entry("key", List.of("value", "anotherValue"))));
        HtmlElement result = parser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("Interesting Givens")).withClassName("interestingGivensHeader"),
                        table(
                                row(
                                        rowData("key").withClassNames("tableEntity", "tableHeaderEntity"),
                                        rowData("[value, anotherValue]").withClassNames("tableEntity", "tableValueEntity")
                                ).withClassName("tableEntity")
                        ).withClassName("tableEntity")
                ).withClassName("interestingGivenContainer"));
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
                        div(content("Interesting Givens")).withClassName("interestingGivensHeader"),
                        table(
                                row(
                                        rowData("key0").withClassNames("tableEntity", "tableHeaderEntity"),
                                        rowData("[k0Avalue, k0Bvalue]").withClassNames("tableEntity", "tableValueEntity")
                                ).withClassName("tableEntity"),
                                row(
                                        rowData("key1").withClassNames("tableEntity", "tableHeaderEntity"),
                                        rowData("[k1Avalue, k1Bvalue]").withClassNames("tableEntity", "tableValueEntity")
                                ).withClassName("tableEntity")
                        ).withClassName("tableEntity")
                ).withClassName("interestingGivenContainer"));
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