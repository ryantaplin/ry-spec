package extension.report.parser.html;

import java.util.ArrayList;
import java.util.List;

public class HtmlTemplateBuilder {

    private List<HtmlValue> elements = new ArrayList<>();
    private String title;

    public static HtmlTemplateBuilder htmlTemplate() {
        return new HtmlTemplateBuilder();
    }

    public HtmlTemplateBuilder withElement(HtmlValue element) {
        elements.add(element);
        return this;
    }

    public HtmlTemplateBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"en\">");
        sb.append("<head>");
        sb.append(String.format("<meta charset=\"%s\"/>", "UTF-8"));
        sb.append(String.format("<title>%s</title>", title));
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<div>");

        elements.forEach(element -> sb.append(element.asString()));

        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }
}
