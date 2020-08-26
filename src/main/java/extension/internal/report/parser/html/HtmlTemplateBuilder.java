package extension.internal.report.parser.html;

import extension.internal.report.parser.html.css.attribute.CssDisplay;
import extension.internal.report.parser.html.css.attribute.CssOverflow;
import extension.internal.report.parser.html.css.attribute.CssPosition;

import java.util.ArrayList;
import java.util.List;

import static extension.internal.report.parser.html.css.CssClassBuilder.cssClass;
import static extension.internal.report.parser.html.css.CssClassBuilder.cssClasses;
import static extension.internal.report.parser.html.css.HtmlTemplateCssBuilder.cssBuilder;
import static extension.internal.report.parser.html.css.attribute.CssBackgroundColour.backgroundColour;
import static extension.internal.report.parser.html.css.attribute.CssBorder.border;
import static extension.internal.report.parser.html.css.attribute.CssBorderCollapse.borderCollapse;
import static extension.internal.report.parser.html.css.attribute.CssBorderCollapseType.COLLAPSE;
import static extension.internal.report.parser.html.css.attribute.CssFontColour.fontColour;
import static extension.internal.report.parser.html.css.attribute.CssFontSize.fontSize;
import static extension.internal.report.parser.html.css.attribute.CssFontWeight.fontWeight;
import static extension.internal.report.parser.html.css.attribute.CssFontWeightType.BOLD;
import static extension.internal.report.parser.html.css.attribute.CssHoverCursor.hoverCursor;
import static extension.internal.report.parser.html.css.attribute.CssMargin.margin;
import static extension.internal.report.parser.html.css.attribute.CssOverflowType.HIDDEN;
import static extension.internal.report.parser.html.css.attribute.CssPadding.padding;
import static extension.internal.report.parser.html.css.attribute.CssUserSelectDisabled.*;
import static extension.internal.report.parser.html.css.attribute.CssWidth.width;
import static extension.internal.report.parser.html.css.attribute.value.CssColour.*;
import static extension.internal.report.parser.html.css.attribute.value.CssCursorType.POINTER;
import static extension.internal.report.parser.html.css.attribute.value.CssDisplayType.NONE;
import static extension.internal.report.parser.html.script.CollapseSiblingsFunction.collapseSiblingsFunctionAsString;

public class HtmlTemplateBuilder {

    private List<HtmlValue> elements = new ArrayList<>();
    private String title;

    public static HtmlTemplateBuilder htmlTemplate() {
        return new HtmlTemplateBuilder();
    }

    public HtmlTemplateBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public HtmlTemplateBuilder withBodyElement(HtmlValue element) {
        this.elements.add(element);
        return this;
    }

    public HtmlTemplateBuilder withBodyElements(List<HtmlValue> elements) {
        this.elements.addAll(elements);
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"en\">");
        sb.append("<head>");
        sb.append(String.format("<meta charset=\"%s\"/>", "UTF-8"));
        sb.append(String.format("<title>%s</title>", title));
        sb.append("<style>");
        sb.append(
                cssBuilder()
                        .addCss(cssClasses("active", "collapsible:hover")
                                .withAttribute(backgroundColour(GREY)))
                        .addCss(cssClass("capturedInteractionsContainer")
                                .withAttribute(border(1))
                                .withAttribute(margin(0, 10, 10, 10)))
                        .addCss(cssClass("capturedInteractionsHeader")
                                .withAttribute(border(1))
                                .withAttribute(fontWeight(BOLD))
                                .withAttribute(padding(5, 5, 5, 5)))
                        .addCss(cssClass("interestingGivensHeader")
                                .withAttribute(border(1))
                                .withAttribute(fontWeight(BOLD))
                                .withAttribute(padding(5, 5, 5, 5)))
                        .addCss(cssClass("interestingGivenContainer")
                                .withAttribute(border(1))
                                .withAttribute(margin(0, 10, 10, 10)))
//                                .withAttribute(padding(2, 2, 2, 2)))
                        .addCss(cssClass("collapsibleContent")
                                .withAttribute(padding(0, 18, 0, 18))
                                .withAttribute(CssDisplay.display(NONE))
                                .withAttribute(CssOverflow.overflow(HIDDEN))
                                .withAttribute(border(1))
                                .withAttribute(backgroundColour(GREY)))
                        .addCss(cssClass("collapsible")
                                .withAttribute(backgroundColour(BLUE))
                                .withAttribute(fontColour(BLACK))
                                .withAttribute(hoverCursor(POINTER))
                                .withAttribute(padding(5, 5, 5, 5))
                                .withAttribute(border(1))
                                .withAttribute(fontSize(15))
                                .withAttribute(webKitUserSelectDisabled())
                                .withAttribute(mozUserSelectDisabled())
                                .withAttribute(msUserSelectDisabled()))
                        .addCss(cssClass("testSpecHeader")
                                .withAttribute(backgroundColour(BLUE))
                                .withAttribute(border(1, CssPosition.BOTTOM))
                                .withAttribute(padding(1, 1, 1, 1)))
                        .addCss(cssClass("sourceCode")
                                .withAttribute(border(1))
                                .withAttribute(margin(10, 10, 0, 10))
                                .withAttribute(padding(5, 5, 5, 5)))
                        .addCss(cssClass("emptyDiv")
                                .withAttribute(margin(5, 2, 10, 2)))
                        .addCss(cssClass("pageTitle")
                                .withAttribute(margin(0, 2, 0, 2))
                                .withAttribute(border(1, CssPosition.BOTTOM))
                                .withAttribute(backgroundColour(BLUE))
                                .withAttribute(fontSize(24)))
                        .addCss(cssClass("container")
                                .withAttribute(margin(5, 5, 5, 5))
                                .withAttribute(border(1)))
                        .addCss(cssClass("tableEntity")
                                .withAttribute(padding(3, 3, 3, 3))
                                .withAttribute(border(1))
                                .withAttribute(width("100%"))
                                .withAttribute(borderCollapse(COLLAPSE)))
                        .addCss(cssClass("tableHeaderEntity")
                                .withAttribute(width("auto"))
                                .withAttribute(fontWeight(BOLD)))
                        .addCss(cssClass("tableValueEntity")
                                .withAttribute(width("100%"))
                        )

                        .build()
        );
        sb.append("</style>");
        sb.append("</head>");
        sb.append("<body style=\"margin:0px\">");
        sb.append("<script>");
        sb.append(collapseSiblingsFunctionAsString());
        sb.append("</script>");
        sb.append("<div>");

        elements.forEach(element -> sb.append(element.asString()));

        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }
}
