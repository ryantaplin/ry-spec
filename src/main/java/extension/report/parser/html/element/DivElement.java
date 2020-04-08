package extension.report.parser.html.element;

import extension.report.parser.html.HtmlValue;
import extension.report.parser.html.css.CssBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class DivElement implements HtmlElement {

    private final List<HtmlValue> contentList;
    private Optional<CssBuilder> cssBuilder;

    public DivElement(List<HtmlValue> contentList) {
        this.contentList = contentList;
        this.cssBuilder = Optional.empty();
    }


    public static DivElement div(HtmlValue... content) {
        return new DivElement(Arrays.asList(content));
    }

    public DivElement with(CssBuilder css) {
        this.cssBuilder = Optional.ofNullable(css);
        return this;
    }

    public String asString() {
        String collectedContent = contentList.stream()
                .map(HtmlValue::asString)
                .collect(joining());
        String cssValue = cssBuilder.map(CssBuilder::asString).orElse("");

        return String.format("<div%s>%s</div>",
                cssValue.isEmpty() ? "" : String.format(" %s", cssValue),
                collectedContent);
    }

}