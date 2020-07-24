package extension.report.parser;

import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.report.parser.html.element.HtmlElement;
import extension.report.parser.html.parser.TestStateToHtmlParser;
import extension.report.parser.html.parser.teststate.CapturedInteractionsToHtmlParser;
import extension.report.parser.html.parser.teststate.InterestingGivensToHtmlParser;
import extension.test.state.TestState;
import org.junit.jupiter.api.Test;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;
import static helpers.assertions.HtmlAssertions.assertThatHtml;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestStateToHtmlParserTest {

    private final TestContentCssHelper cssHelper = mock(TestContentCssHelper.class);
    private final InterestingGivensToHtmlParser interestingGivensParser = mock(InterestingGivensToHtmlParser.class);
    private final CapturedInteractionsToHtmlParser capturedInteractionsParser = mock(CapturedInteractionsToHtmlParser.class);
    private final TestStateToHtmlParser testStateToHtmlParser = new TestStateToHtmlParser(interestingGivensParser, capturedInteractionsParser, cssHelper);

    private final TestState state = mock(TestState.class);

    @Test
    void returnsDivElementWithElementReturnedByInterestingGivensParser() {
        when(interestingGivensParser.parse(state)).thenReturn(div(content("some-given")));
        HtmlElement result = testStateToHtmlParser.parse(state);
        assertThatHtml(result).isEqualTo(div(div(content("some-given"))));
    }

    @Test
    void returnsDivElementWithElementReturnedByCapturedInteractionsParser() {
        when(capturedInteractionsParser.parse(state)).thenReturn(div(content("some-interaction")));
        HtmlElement result = testStateToHtmlParser.parse(state);
        assertThatHtml(result).isEqualTo(div(div(content("some-interaction"))));
    }

    @Test
    void returnsDivElementWithInterestingGivensElementAndCapturedInteractionsElementInOrder() {
        when(interestingGivensParser.parse(state)).thenReturn(div(content("some-given")));
        when(capturedInteractionsParser.parse(state)).thenReturn(div(content("some-interaction")));
        HtmlElement result = testStateToHtmlParser.parse(state);
        assertThatHtml(result).isEqualTo(
                div(
                        div(content("some-given")),
                        div(content("some-interaction"))
                )
        );
    }

    @Test
    void returnsEmptyDivWhenNullStateIsProvided() {
        HtmlElement result = testStateToHtmlParser.parse(null);
        assertThatHtml(result).isEqualTo(div());
    }
}