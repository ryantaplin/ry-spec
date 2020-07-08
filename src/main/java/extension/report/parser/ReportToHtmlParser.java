package extension.report.parser;

import extension.test.TestSpecimen;
import extension.report.parser.helper.CamelCaseSplitter;
import extension.report.parser.html.HtmlValue;
import extension.report.parser.html.css.CssBuilder;
import extension.report.parser.html.css.CssPosition;

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

    public String parse(TestSpecimen report) {
        HtmlValue pageTitle = formatPageTitle(report.getClassPath());
        return htmlTemplate()
                .withTitle(pageTitle.asString())
                .withElement(div(div(pageTitle).with(css().margin(0, 2, 0, 2)))
                        .with(pageTitleCss()))
                .withElements(testSourceCodeToHtmlParser.parse(report.getTestMethodData()))
                .build();
    }

    private CssBuilder pageTitleCss() {
        return css()
                .border(1, CssPosition.BOTTOM)
                .backgroundColour(BLUE)
                .fontSize(24);
    }

    private HtmlValue formatPageTitle(String title) {
        String[] split = title.split("/");
        return content(String.format("%s", camelCaseSplitter.split(split[split.length - 1])));
    }
}