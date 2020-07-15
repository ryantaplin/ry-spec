package extension.report.parser.html.parser;

import extension.report.parser.helper.CamelCaseSplitter;
import extension.report.parser.helper.SentenceFormatter;
import extension.report.parser.html.HtmlValue;
import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.test.TestResult;

import java.util.List;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;

public class TestHeaderToHtmlParser {

    private final SentenceFormatter formatter;
    private final TestContentCssHelper cssHelper;

    public TestHeaderToHtmlParser(SentenceFormatter formatter, TestContentCssHelper cssHelper) {
        this.formatter = formatter;
        this.cssHelper = cssHelper;
    }

    public HtmlValue parse(String name, TestResult result) {
        return div(content(String.format("%s : %s", formatter.format(name), result.toString())))
                .with(cssHelper.headerCss());
    }
}
