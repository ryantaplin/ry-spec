package extension.report.parser.html.parser.teststate;

import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.report.parser.html.element.HtmlElement;
import extension.test.state.CapturedInteraction;
import extension.test.state.TestState;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;

public class CapturedInteractionsToHtmlParser {

    private final TestContentCssHelper cssHelper;

    public CapturedInteractionsToHtmlParser(TestContentCssHelper cssHelper) {
        this.cssHelper = cssHelper;
    }

    public HtmlElement parse(TestState testState) {
        return Optional.ofNullable(testState)
                .map(TestState::getCapturedInteractions)
                .flatMap(this::parseCapturedInteractions)
//                .map(element -> element.with(cssHelper.capturedInterestingElementCss()))
                .orElse(null);
    }

    private Optional<HtmlElement> parseCapturedInteractions(List<CapturedInteraction> interactions) {
        List<HtmlElement> capturedInteractionElements = interactions.stream()
                .map(this::toKeyValuesMapping)
                .collect(Collectors.toList());

        if (!capturedInteractionElements.isEmpty()) {
            return Optional.of(
                    div(
                            div(content("Captured Interactions")),
                            div(capturedInteractionElements)
                    ).with(cssHelper.capturedInterestingElementCss())
            );
        }
        return Optional.empty();
    }

    private HtmlElement toKeyValuesMapping(CapturedInteraction capturedInteraction) {
        return div(
                div(content(capturedInteraction.getInteractionParticipants())).withClassName("collapsible").withOnClickFunction("collapseSiblingsFunction(this)").with(cssHelper.collapsibleButton()),
                div(
                        content(capturedInteraction.getInteractionValue()
                                .map(Object::toString) //TODO: custom object parsing
                                .orElse(""))
                ).withClassName("collapsible-content").with(cssHelper.collapsibleContent())
        );
    }
}