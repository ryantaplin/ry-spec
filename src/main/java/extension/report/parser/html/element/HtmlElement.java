package extension.report.parser.html.element;

import extension.report.parser.html.HtmlValue;
import extension.report.parser.html.css.CssElements;

public interface HtmlElement extends HtmlValue {
    HtmlElement with(CssElements cssElements);
    HtmlElement withClassName(String className);

    HtmlElement withOnClickFunction(String s);
}