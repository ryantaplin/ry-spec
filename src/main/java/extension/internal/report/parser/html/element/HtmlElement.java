package extension.internal.report.parser.html.element;

import extension.internal.report.parser.html.HtmlValue;

public interface HtmlElement extends HtmlValue {
    HtmlElement withClassName(String className);

    HtmlElement withOnClickFunction(String s);
}