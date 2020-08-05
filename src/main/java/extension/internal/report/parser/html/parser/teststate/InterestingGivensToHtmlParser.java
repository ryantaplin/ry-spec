package extension.internal.report.parser.html.parser.teststate;

import extension.TestState;
import extension.internal.report.parser.html.element.HtmlElement;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;

public class InterestingGivensToHtmlParser {

    public HtmlElement parse(TestState testState) {
        return Optional.ofNullable(testState)
                .map(TestState::getInterestingGivenEntries)
                .flatMap(this::parseInterestingGivens)
                .map(element -> element.withClassName("interestingGivenContainer"))
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
                    )
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