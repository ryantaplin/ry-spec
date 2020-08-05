package extension.internal.report.parser.html.parser;

import extension.internal.domain.test.method.Result;
import extension.internal.report.parser.helper.SentenceFormatter;
import extension.internal.report.parser.html.HtmlValue;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;

public class StringToHtmlHeaderParser {

    private final SentenceFormatter formatter;

    public StringToHtmlHeaderParser(SentenceFormatter formatter) {
        this.formatter = formatter;
    }

    public HtmlValue parse(String name, Result result) {
        return div(
                content(String.format("%s : %s", formatter.format(name), result.toString()))
        ).withClassName("testSpecHeader");
    }
}
