package extension.report.parser.html;

import java.util.ArrayList;
import java.util.List;

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

    public HtmlTemplateBuilder withElement(HtmlValue element) {
        this.elements.add(element);
        return this;
    }

    public HtmlTemplateBuilder withElements(List<HtmlValue> elements) {
        this.elements.addAll(elements);
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"en\" style=\"height: 98%\">");
        sb.append("<head>");
        sb.append(String.format("<meta charset=\"%s\"/>", "UTF-8"));
        sb.append(String.format("<title>%s</title>", title));
        sb.append("</head>");
        sb.append("<body style=\"border: solid 1px black; height: 100%\">");
        sb.append("<div>");

        elements.forEach(element -> sb.append(element.asString()));

        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }
}
