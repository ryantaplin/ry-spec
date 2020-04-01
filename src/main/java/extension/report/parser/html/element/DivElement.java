package extension.report.parser.html.element;

import extension.report.parser.html.HtmlValue;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

public class DivElement implements HtmlElement {

    private final List<HtmlValue> contentList;

    public DivElement(List<HtmlValue> contentList) {
        this.contentList = contentList;
    }

    public static DivElement div(HtmlValue... content) {
        return new DivElement(asList(content));
    }

    public String asString() {
        String collectedContent = contentList.stream()
                .map(HtmlValue::asString).collect(joining());
        return String.format("<div>%s</div>", collectedContent);
    }

}