package extension.internal.report.parser.html.parser.teststate;

import extension.internal.report.parser.html.element.HtmlElement;
import extension.defaults.DefaultCapturedInteraction;
import extension.defaults.DefaultTestState;
import extension.TestState;
import org.junit.jupiter.api.Test;

import java.util.List;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;
import static helpers.assertions.HtmlAssertions.assertThatHtml;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CapturedInteractionsToHtmlParserTest {

    private final TestState state = mock(TestState.class);

    private final CapturedInteractionsToHtmlParser capturedInteractionsParser = new CapturedInteractionsToHtmlParser();

    @Test
    void returnsDivElementWithCapturedInteraction() {
        when(state.getCapturedInteractions()).thenReturn(List.of(new DefaultCapturedInteraction("sender", "receiver", "anyValue")));
        HtmlElement result = capturedInteractionsParser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("Captured Interactions")).withClassName("capturedInteractionsHeader"),
                        div(
                                div(
                                        div(content("sender to receiver")).withClassName("collapsible").withOnClickFunction("collapseSiblingsFunction(this)"),
                                        div(content("anyValue")).withClassName("collapsibleContent"))
                        )
                ).withClassName("capturedInteractionsContainer"));
    }

    @Test
    void returnsDivElementWithMultipleCapturedInteractions() {
        when(state.getCapturedInteractions()).thenReturn(List.of(
                new DefaultCapturedInteraction("sender", "receiver", "anyValue"),
                new DefaultCapturedInteraction("anotherSender", "anotherReceiver", "anotherValue")
        ));
        HtmlElement result = capturedInteractionsParser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("Captured Interactions")).withClassName("capturedInteractionsHeader"),
                        div(
                                div(
                                        div(content("sender to receiver")).withClassName("collapsible").withOnClickFunction("collapseSiblingsFunction(this)"),
                                        div(content("anyValue")).withClassName("collapsibleContent")),
                                div(
                                        div(content("anotherSender to anotherReceiver")).withClassName("collapsible").withOnClickFunction("collapseSiblingsFunction(this)"),
                                        div(content("anotherValue")).withClassName("collapsibleContent")
                                )
                        )
                ).withClassName("capturedInteractionsContainer"));
    }

    @Test
    void returnsNullWhenStateIsNull() {
        HtmlElement result = capturedInteractionsParser.parse(null);
        assertThat(result).isNull();
    }

    @Test
    void returnsNullWhenStateHasNoInterestingGivens() {
        HtmlElement result = capturedInteractionsParser.parse(new DefaultTestState());
        assertThat(result).isNull();
    }
}