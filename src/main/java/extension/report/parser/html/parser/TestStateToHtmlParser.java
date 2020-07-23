package extension.report.parser.html.parser;

import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.report.parser.html.element.HtmlElement;
import extension.test.state.TestState;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;

public class TestStateToHtmlParser {

    private final TestContentCssHelper cssHelper;

    public TestStateToHtmlParser(TestContentCssHelper cssHelper) {
        this.cssHelper = cssHelper;
    }

    public HtmlElement parse(TestState testState) {
        return Optional.ofNullable(testState)
                .filter(state -> state.getInterestingEntryList().size() > 0)
                .map(state -> parseInterestingContent(state).with(cssHelper.capturedInterestingElementCss()))
                .orElse(div().with(cssHelper.emptyDivPadding()));
    }

    private HtmlElement parseInterestingContent(TestState state) {
        return div(
                div(content("Interesting Givens")),
                div(
                        Optional.ofNullable(state)
                                .map(TestState::getInterestingEntryList)
                                .orElse(Collections.emptyList()).stream()
                                .map(this::toKeyValuesMapping)
                                .collect(Collectors.toList())
                )
        );
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
