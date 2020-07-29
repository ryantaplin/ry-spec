package extension.internal.report.parser.html.parser;

import extension.internal.report.parser.html.css.helper.TestContentCssHelper;
import extension.internal.report.parser.html.element.HtmlElement;
import extension.internal.report.parser.html.parser.teststate.CapturedInteractionsToHtmlParser;
import extension.internal.report.parser.html.parser.teststate.InterestingGivensToHtmlParser;
import extension.TestState;
import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;
import static helpers.assertions.HtmlAssertions.assertThatHtml;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StateToHtmlParserTest {

    private final TestContentCssHelper cssHelper = mock(TestContentCssHelper.class);
    private final InterestingGivensToHtmlParser interestingGivensParser = mock(InterestingGivensToHtmlParser.class);
    private final CapturedInteractionsToHtmlParser capturedInteractionsParser = mock(CapturedInteractionsToHtmlParser.class);
    private final StateToHtmlParser stateToHtmlParser = new StateToHtmlParser(interestingGivensParser, capturedInteractionsParser, cssHelper);

    private final TestState state = mock(TestState.class);

    @Test
    void returnsDivElementWithElementReturnedByInterestingGivensParser() {
        when(interestingGivensParser.parse(state)).thenReturn(div(content("some-given")));
        HtmlElement result = stateToHtmlParser.parse(state);
        assertThatHtml(result).isEqualTo(div(div(content("some-given"))));
    }

    @Test
    void returnsDivElementWithElementReturnedByCapturedInteractionsParser() {
        when(capturedInteractionsParser.parse(state)).thenReturn(div(content("some-interaction")));
        HtmlElement result = stateToHtmlParser.parse(state);
        assertThatHtml(result).isEqualTo(div(div(content("some-interaction"))));
    }

    @Test
    void returnsDivElementWithInterestingGivensElementAndCapturedInteractionsElementInOrder() {
        when(interestingGivensParser.parse(state)).thenReturn(div(content("some-given")));
        when(capturedInteractionsParser.parse(state)).thenReturn(div(content("some-interaction")));
        HtmlElement result = stateToHtmlParser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("some-given")),
                        div(content("some-interaction"))
                )
        );
    }

    @Test
    void returnsEmptyDivWhenNullStateIsProvided() {
        HtmlElement result = stateToHtmlParser.parse(null);
        assertThatHtml(result).isEqualTo(div());
    }
}