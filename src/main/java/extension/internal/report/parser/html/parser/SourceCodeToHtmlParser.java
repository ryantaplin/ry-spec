package extension.internal.report.parser.html.parser;

import extension.internal.report.parser.helper.SourceCodeParser;
import extension.internal.report.parser.html.HtmlValue;
import extension.internal.report.parser.html.css.helper.TestContentCssHelper;
import extension.internal.domain.test.method.MethodSourceCode;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;

public class SourceCodeToHtmlParser {

    private final TestContentCssHelper cssHelper;
    private final SourceCodeParser sourceCodeFormatter;

    public SourceCodeToHtmlParser(SourceCodeParser sourceCodeFormatter, TestContentCssHelper cssHelper) {
        this.sourceCodeFormatter = sourceCodeFormatter;
        this.cssHelper = cssHelper;
    }

    public HtmlValue parse(MethodSourceCode sourceCode) {
        return div(content(sourceCodeFormatter.format(sourceCode)))
                .with(cssHelper.sourceCodeCss());
    }
}
