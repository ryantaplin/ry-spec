package extension.report.parser.html.element;

import extension.report.parser.html.HtmlValue;
import extension.report.parser.html.css.CssElements;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class DivElement implements HtmlElement {

    private final List<HtmlValue> contentList;
    private Optional<CssElements> cssBuilder;

    public DivElement(List<HtmlValue> contentList) {
        this.contentList = contentList;
        this.cssBuilder = Optional.empty();
    }


    public static DivElement div(HtmlValue... content) {
        return new DivElement(Arrays.asList(content));
    }

    public static DivElement div(List<HtmlValue> content) {
        return new DivElement(content);
    }

    public DivElement with(CssElements css) {
        this.cssBuilder = Optional.ofNullable(css);
        return this;
    }

    public String asString() {
        String collectedContent = contentList.stream()
                .filter(Objects::nonNull)
                .map(HtmlValue::asString)
                .collect(joining());
        String cssValue = cssBuilder.map(CssElements::asString).orElse("");

        return String.format("<div%s>%s</div>",
                cssValue.isEmpty() ? "" : String.format(" %s", cssValue),
                collectedContent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivElement that = (DivElement) o;
        return Objects.equals(contentList, that.contentList) &&
                Objects.equals(cssBuilder, that.cssBuilder);
    }
}