package extension.report.parser;

import extension.report.parser.html.HtmlContent;
import extension.report.parser.html.HtmlValue;
import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.report.parser.html.element.DivElement;
import extension.report.parser.html.parser.TestHeaderToHtmlParser;
import extension.report.parser.html.parser.TestSourceCodeToHtmlParser;
import extension.report.parser.html.parser.TestStateToHtmlParser;
import extension.test.TestMethodData;
import extension.test.TestMethodSourceCode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;

public class TestSpecimenToHtmlWorker {

    private final TestContentCssHelper cssHelper;

    private final TestStateToHtmlParser stateParser;
    private final TestHeaderToHtmlParser headerParser;
    private final TestSourceCodeToHtmlParser sourceCodeParser;

    //SourceCodeParser sourceCodeParser, CamelCaseSplitter camelCaseSplitter, SentenceFormatter sentenceFormatter,

    public TestSpecimenToHtmlWorker(TestHeaderToHtmlParser headerParser,
                                    TestSourceCodeToHtmlParser sourceCodeParser,
                                    TestStateToHtmlParser stateParser,
                                    TestContentCssHelper cssHelper) {
        this.headerParser = headerParser;
        this.sourceCodeParser = sourceCodeParser;
        this.stateParser = stateParser;
        this.cssHelper = cssHelper;
    }

    public List<HtmlValue> parse(List<TestMethodData> testMethodData) {
        return testMethodData.stream()
                .map(this::toResult)
                .collect(Collectors.toList());
    }

    private DivElement toResult(TestMethodData data) {
        return div(
                headerParser.parse(data.getName(), data.getResult()),
                sourceCodeParser.parse(data.getSourceCode()),
                stateParser.parse(data.getOptionalState().orElse(null))
        ).with(cssHelper.containerCss());
    }
}
