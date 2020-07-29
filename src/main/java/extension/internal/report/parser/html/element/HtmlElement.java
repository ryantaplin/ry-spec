package extension.internal.report.parser.html.element;

import extension.internal.report.parser.html.HtmlValue;
import extension.internal.report.parser.html.css.CssElements;

public interface HtmlElement extends HtmlValue {
    HtmlElement with(CssElements cssElements);
    HtmlElement withClassName(String className);

    HtmlElement withOnClickFunction(String s);
}