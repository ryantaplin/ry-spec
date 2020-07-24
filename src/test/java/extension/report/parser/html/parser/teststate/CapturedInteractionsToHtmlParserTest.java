package extension.report.parser.html.parser.teststate;

import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.report.parser.html.element.HtmlElement;
import extension.test.state.CapturedInteraction;
import extension.test.state.DefaultTestState;
import extension.test.state.TestState;
import org.junit.jupiter.api.Test;

import java.util.List;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;
import static helpers.assertions.HtmlAssertions.assertThatHtml;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CapturedInteractionsToHtmlParserTest {

    private final TestContentCssHelper cssHelper = mock(TestContentCssHelper.class);
    private final CapturedInteractionsToHtmlParser capturedInteractionsParser = new CapturedInteractionsToHtmlParser(cssHelper);

    private final TestState state = mock(TestState.class);


    @Test
    void returnsDivElementWithCapturedInteraction() {
        when(state.getCapturedInteractions()).thenReturn(List.of(new CapturedInteraction("sender", "receiver", "anyValue")));
        HtmlElement result = capturedInteractionsParser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("Captured Interactions")),
                        div(div(content("sender to receiver = "), content("anyValue")))
                ));
    }

    @Test
    void returnsDivElementWithMultipleCapturedInteractions() {
        when(state.getCapturedInteractions()).thenReturn(List.of(
                new CapturedInteraction("sender", "receiver", "anyValue"),
                new CapturedInteraction("anotherSender", "anotherReceiver", "anotherValue")
        ));
        HtmlElement result = capturedInteractionsParser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("Captured Interactions")),
                        div(
                                div(content("sender to receiver = "), content("anyValue")),
                                div(content("anotherSender to anotherReceiver = "), content("anotherValue"))
                        )
                ));
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