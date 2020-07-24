package extension.report.parser.html.parser;

import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.report.parser.html.element.DivElement;
import extension.report.parser.html.element.HtmlElement;
import extension.report.parser.html.parser.teststate.CapturedInteractionsToHtmlParser;
import extension.report.parser.html.parser.teststate.InterestingGivensToHtmlParser;
import extension.test.state.TestState;

import java.util.Optional;

import static extension.report.parser.html.element.DivElement.div;

public class TestStateToHtmlParser {

    private InterestingGivensToHtmlParser interestingGivensToHtmlParser;
    private CapturedInteractionsToHtmlParser capturedInteractionsParser;
    private final TestContentCssHelper cssHelper;

    public TestStateToHtmlParser(InterestingGivensToHtmlParser interestingGivensParser, CapturedInteractionsToHtmlParser capturedInteractionsParser, TestContentCssHelper cssHelper) {
        this.interestingGivensToHtmlParser = interestingGivensParser;
        this.capturedInteractionsParser = capturedInteractionsParser;
        this.cssHelper = cssHelper;
    }

    public HtmlElement parse(TestState testState) {
        return Optional.of(div(
                interestingGivensToHtmlParser.parse(testState),
                capturedInteractionsParser.parse(testState)
        )).filter(DivElement::isNotEmpty).orElse(div().with(cssHelper.emptyDivPadding()));
    }
}
