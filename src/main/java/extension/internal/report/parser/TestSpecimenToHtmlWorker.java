package extension.internal.report.parser;

import extension.internal.report.parser.html.HtmlValue;
import extension.internal.report.parser.html.element.DivElement;
import extension.internal.report.parser.html.parser.StringToHtmlHeaderParser;
import extension.internal.report.parser.html.parser.SourceCodeToHtmlParser;
import extension.internal.report.parser.html.parser.StateToHtmlParser;
import extension.internal.domain.test.method.MethodData;

import java.util.List;
import java.util.stream.Collectors;

import static extension.internal.report.parser.html.element.DivElement.div;

public class TestSpecimenToHtmlWorker {

    private final StateToHtmlParser stateParser;
    private final StringToHtmlHeaderParser headerParser;
    private final SourceCodeToHtmlParser sourceCodeParser;

    public TestSpecimenToHtmlWorker(StringToHtmlHeaderParser headerParser,
                                    SourceCodeToHtmlParser sourceCodeParser,
                                    StateToHtmlParser stateParser) {
        this.headerParser = headerParser;
        this.sourceCodeParser = sourceCodeParser;
        this.stateParser = stateParser;
    }

    public List<HtmlValue> parse(List<MethodData> methodData) {
        return methodData.stream()
                .map(this::toResult)
                .collect(Collectors.toList());
    }

    private DivElement toResult(MethodData data) {
        return div(
                headerParser.parse(data.getName(), data.getResult()),
                sourceCodeParser.parse(data.getSourceCode()),
                stateParser.parse(data.getOptionalState().orElse(null))
        ).withClassName("container");
    }
}
