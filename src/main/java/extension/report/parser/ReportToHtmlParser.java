package extension.report.parser;

import extension.report.builder.ReportBuilder;
import extension.report.parser.helper.CamelCaseParser;
import extension.report.parser.helper.SourceCodeParser;
import extension.report.parser.html.HtmlValue;
import extension.test.TestMethodData;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.HtmlTemplateBuilder.htmlTemplate;
import static extension.report.parser.html.css.CssBuilder.css;
import static extension.report.parser.html.css.CssColour.BLUE;
import static extension.report.parser.html.element.DivElement.div;

public class ReportToHtmlParser implements ReportParser {

    private CamelCaseParser camelCaseToSentenceParser;
    private SourceCodeParser sourceCodeParser;

    public ReportToHtmlParser(CamelCaseParser camelCaseToSentenceParser, SourceCodeParser sourceCodeParser) {
        this.camelCaseToSentenceParser = camelCaseToSentenceParser;
        this.sourceCodeParser = sourceCodeParser;
    }

    public String parse(ReportBuilder report) {
        HtmlValue pageTitle = formatPageTitle(report.getClassPath());
        return htmlTemplate()
                .withTitle(pageTitle.asString()) //style="border-bottom: solid 1px black; height: 6%"
                .withElement(div(pageTitle).with(css().backgroundColour(BLUE)))
                .withElement(div(testReportContent(report.getTestMethodData())))
                .build();
    }

    private HtmlValue testReportContent(List<TestMethodData> testMethodData) {
        return content(testMethodData.stream()
                .map(e -> testBox(e).asString())
                .collect(Collectors.joining()));
    }

    private HtmlValue testBox(TestMethodData t) {
        return div(
                div(content(camelCaseToSentenceParser.parse(t.getName()) + " : " + t.getResult().toString())),
                div(content(Stream.of(sourceCodeParser.parse(t.getSourceCode().asString()).split("\n"))
                        .map(camelCaseToSentenceParser::parse)
                        .collect(Collectors.joining("\n"))))
        );
    }

    private HtmlValue formatPageTitle(String title) {
        String[] split = title.split("/");
        return content(String.format("%s", split[split.length - 1]));
    }
}
