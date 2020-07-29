package extension.internal.report.parser.html.parser;

import extension.internal.report.parser.helper.SentenceFormatter;
import extension.internal.report.parser.html.HtmlValue;
import extension.internal.report.parser.html.css.helper.TestContentCssHelper;
import extension.internal.domain.test.method.Result;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;

public class StringToHtmlHeaderParser {

    private final SentenceFormatter formatter;
    private final TestContentCssHelper cssHelper;

    public StringToHtmlHeaderParser(SentenceFormatter formatter, TestContentCssHelper cssHelper) {
        this.formatter = formatter;
        this.cssHelper = cssHelper;
    }

    public HtmlValue parse(String name, Result result) {
        return div(content(String.format("%s : %s", formatter.format(name), result.toString())))
                .with(cssHelper.headerCss());
    }
}
