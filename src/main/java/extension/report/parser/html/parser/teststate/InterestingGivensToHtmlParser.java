package extension.report.parser.html.parser.teststate;

import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.report.parser.html.element.HtmlElement;
import extension.test.state.TestState;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;

public class InterestingGivensToHtmlParser {

    private final TestContentCssHelper cssHelper;

    public InterestingGivensToHtmlParser(TestContentCssHelper cssHelper) {
        this.cssHelper = cssHelper;
    }

    public HtmlElement parse(TestState testState) {
        return Optional.ofNullable(testState)
                .map(TestState::getInterestingGivenEntries)
                .flatMap(this::parseInterestingGivens)
                .map(element -> element.with(cssHelper.capturedInterestingElementCss()))
                .orElse(null);

    }

    private Optional<HtmlElement> parseInterestingGivens(List<Map.Entry<String, List<Object>>> entries) {
        List<HtmlElement> interestingGivensAsHtml = entries.stream()
                .map(this::toKeyValuesMapping)
                .collect(Collectors.toList());

        if (!interestingGivensAsHtml.isEmpty()) {
            return Optional.of(
                    div(
                            div(content("Interesting Givens")),
                            div(interestingGivensAsHtml)
                    ).with(cssHelper.capturedInterestingElementCss())
            );
        }
        return Optional.empty();
    }

    private HtmlElement toKeyValuesMapping(Map.Entry<String, List<Object>> entry) {
        return div(
                content(entry.getKey() + ": "),
                content(entry.getValue().stream()
                        .map(Object::toString) //TODO: custom object parsing
                        .collect(Collectors.joining(", ")))
        );
    }
}