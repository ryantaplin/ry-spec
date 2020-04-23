package extension.report.parser;

import extension.report.parser.helper.CamelCaseSplitter;
import extension.report.parser.helper.SentenceFormatter;
import extension.report.parser.helper.SourceCodeParser;
import extension.report.parser.html.HtmlContent;
import extension.report.parser.html.HtmlValue;
import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.test.TestMethodData;
import extension.test.TestMethodSourceCode;
import extension.test.TestResult;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;

public class TestSourceCodeToHtmlParser {

    private final SourceCodeParser sourceCodeParser;

    private final CamelCaseSplitter camelCaseSplitter;
    private final SentenceFormatter sentenceFormatter;
    private final TestContentCssHelper cssHelper;


    public TestSourceCodeToHtmlParser(SourceCodeParser sourceCodeParser, CamelCaseSplitter camelCaseSplitter, SentenceFormatter sentenceFormatter, TestContentCssHelper cssHelper) {
        this.sourceCodeParser = sourceCodeParser;
        this.camelCaseSplitter = camelCaseSplitter;
        this.sentenceFormatter = sentenceFormatter;
        this.cssHelper = cssHelper;
    }

    public List<HtmlValue> parse(List<TestMethodData> testMethodData) {
        return testMethodData.stream()
                .map(data -> div(
                        div(generateHeaderContent(data.getName(), data.getResult())).with(cssHelper.headerCss()),
                        div(generateBodyContent(data.getSourceCode())).with(cssHelper.bodyCss())
                ).with(cssHelper.containerCss()))
                .collect(Collectors.toList());
    }

    private HtmlContent generateHeaderContent(String name, TestResult result) {
        return content(String.format("%s : %s", sentenceFormatter.format(camelCaseSplitter.split(name)), result.toString()));
    }

    private HtmlContent generateBodyContent(TestMethodSourceCode sourceCode) {
        String[] sourceCodeLines = sourceCodeParser.parse(sourceCode.asString()).split("\n");
        return content(Stream.of(sourceCodeLines)
                .map(this::sourceCodeLineFormat)
                .collect(Collectors.joining("\n")));
    }

    private String sourceCodeLineFormat(String s) {
        return sentenceFormatter.format(camelCaseSplitter.split(s));
    }
}
