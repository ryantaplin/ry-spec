package extension.report.parser.html.parser;

import extension.report.parser.helper.SourceCodeParser;
import extension.report.parser.html.HtmlValue;
import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.test.TestMethodSourceCode;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;

public class TestSourceCodeToHtmlParser {

    private final TestContentCssHelper cssHelper;
    private final SourceCodeParser sourceCodeFormatter;

    public TestSourceCodeToHtmlParser(SourceCodeParser sourceCodeFormatter, TestContentCssHelper cssHelper) {
        this.sourceCodeFormatter = sourceCodeFormatter;
        this.cssHelper = cssHelper;
    }

    public HtmlValue parse(TestMethodSourceCode sourceCode) {
        return div(content(sourceCodeFormatter.format(sourceCode)))
                .with(cssHelper.sourceCodeCss());
    }
}
