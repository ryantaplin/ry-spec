package extension.internal.report.parser.html;

import java.util.ArrayList;
import java.util.List;

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
        sb.append(".active, .collapsible:hover {\n" +
                "  background-color: #ccc;\n" +
                "}\n"); //TODO: extract out... maybe change objects to only take class/id and have all css centralised here.
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
