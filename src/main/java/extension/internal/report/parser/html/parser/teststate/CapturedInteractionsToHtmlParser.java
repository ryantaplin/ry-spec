package extension.internal.report.parser.html.parser.teststate;

import extension.CapturedInteraction;
import extension.TestState;
import extension.internal.report.parser.html.element.HtmlElement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;

public class CapturedInteractionsToHtmlParser {

    public HtmlElement parse(TestState testState) {
        return Optional.ofNullable(testState)
                .map(TestState::getCapturedInteractions)
                .flatMap(this::parseCapturedInteractions)
                .orElse(null);
    }

    private Optional<HtmlElement> parseCapturedInteractions(List<CapturedInteraction> interactions) {
        List<HtmlElement> capturedInteractionElements = interactions.stream()
                .map(this::toKeyValuesMapping)
                .collect(Collectors.toList());

        if (!capturedInteractionElements.isEmpty()) {
            return Optional.of(
                    div(
                            div(content("Captured Interactions")).withClassName("capturedInteractionsHeader"),
                            div(capturedInteractionElements)
                    ).withClassName("capturedInteractionsContainer")
            );
        }
        return Optional.empty();
    }

    private HtmlElement toKeyValuesMapping(CapturedInteraction capturedInteraction) {
        return div(
                div(content(capturedInteraction.getInteractionParticipants()))
                        .withOnClickFunction("collapseSiblingsFunction(this)")
                        .withClassName("collapsible"),
                div(content(capturedInteraction.getInteractionValue().map(Object::toString).orElse("")))
                        .withClassName("collapsibleContent")
        );
    }
}