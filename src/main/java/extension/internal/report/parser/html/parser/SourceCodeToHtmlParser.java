package extension.internal.report.parser.html.parser;

import extension.internal.report.parser.helper.SourceCodeParser;
import extension.internal.report.parser.html.HtmlValue;
import extension.internal.domain.test.method.MethodSourceCode;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;

public class SourceCodeToHtmlParser {

    private final SourceCodeParser sourceCodeFormatter;

    public SourceCodeToHtmlParser(SourceCodeParser sourceCodeFormatter) {
        this.sourceCodeFormatter = sourceCodeFormatter;
    }

    public HtmlValue parse(MethodSourceCode sourceCode) {
        return div(content(sourceCodeFormatter.format(sourceCode)))
                .withClassName("sourceCode");
    }
}
