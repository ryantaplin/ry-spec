package extension.report.parser;

import extension.report.builder.ReportBuilder;
import extension.report.parser.helper.CamelCaseSplitter;
import extension.report.parser.helper.SentenceFormatter;
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

    private CamelCaseSplitter camelCaseSplitter;
    private TestSourceCodeToHtmlParser testSourceCodeToHtmlParser;

    public ReportToHtmlParser(CamelCaseSplitter camelCaseSplitter, TestSourceCodeToHtmlParser testSourceCodeToHtmlParser) {
        this.camelCaseSplitter = camelCaseSplitter;
        this.testSourceCodeToHtmlParser = testSourceCodeToHtmlParser;
    }

    public String parse(ReportBuilder report) {
        HtmlValue pageTitle = formatPageTitle(report.getClassPath());
        return htmlTemplate()
                .withTitle(pageTitle.asString()) //style="border-bottom: solid 1px black; height: 6%"
                .withElement(div(pageTitle).with(css().backgroundColour(BLUE).fontSize(24)))
                .withElements(testSourceCodeToHtmlParser.parse(report.getTestMethodData()))
                .build();
    }

    private HtmlValue formatPageTitle(String title) {
        String[] split = title.split("/");
        return content(String.format("%s", camelCaseSplitter.split(split[split.length - 1])));
    }
}