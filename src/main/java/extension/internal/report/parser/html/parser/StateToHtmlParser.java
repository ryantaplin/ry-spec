package extension.internal.report.parser.html.parser;

import extension.internal.report.parser.html.element.DivElement;
import extension.internal.report.parser.html.element.HtmlElement;
import extension.internal.report.parser.html.parser.teststate.CapturedInteractionsToHtmlParser;
import extension.internal.report.parser.html.parser.teststate.InterestingGivensToHtmlParser;
import extension.TestState;

import java.util.Optional;

import static extension.internal.report.parser.html.element.DivElement.div;

public class StateToHtmlParser {

    private InterestingGivensToHtmlParser interestingGivensToHtmlParser;
    private CapturedInteractionsToHtmlParser capturedInteractionsParser;

    public StateToHtmlParser(InterestingGivensToHtmlParser interestingGivensParser, CapturedInteractionsToHtmlParser capturedInteractionsParser) {
        this.interestingGivensToHtmlParser = interestingGivensParser;
        this.capturedInteractionsParser = capturedInteractionsParser;
    }

    public HtmlElement parse(TestState testState) {
        return Optional.of(div(
                interestingGivensToHtmlParser.parse(testState),
                capturedInteractionsParser.parse(testState)
        )).filter(DivElement::isNotEmpty).orElse(div().withClassName("emptyDiv"));
    }
}
