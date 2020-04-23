package extension.report.parser.html.css.helper;

import extension.report.parser.html.css.CssBuilder;
import extension.report.parser.html.css.CssColour;
import extension.report.parser.html.css.CssPosition;

import static extension.report.parser.html.css.CssBuilder.css;

public class TestContentCssHelper {

    public CssBuilder headerCss() {
        return css()
                .backgroundColour(CssColour.BLUE)
                .border(1, CssPosition.BOTTOM)
                .padding(1, 1, 1, 1);
    }

    public CssBuilder bodyCss() {
        return css()
                .padding(2, 2, 2, 2);
    }

    public CssBuilder containerCss() {
        return css()
                .margin(5, 5, 5, 5)
                .border(1);
    }
}
