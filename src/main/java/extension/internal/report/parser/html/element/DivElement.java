package extension.internal.report.parser.html.element;

import extension.internal.report.parser.html.HtmlValue;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class DivElement implements HtmlElement {

    private final List<? extends HtmlValue> contentList;
    private String className;
    private String onClickFunction;

    public DivElement(List<? extends HtmlValue> contentList) {
        this.contentList = contentList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static DivElement div(HtmlValue... content) {
        return new DivElement(Arrays.asList(content));
    }

    public static DivElement div(List<? extends HtmlValue> content) {
        return new DivElement(content);
    }

    @Override
    public DivElement withClassName(String className) {
        this.className = className;
        return this;
    }

    @Override
    public DivElement withOnClickFunction(String onClickFunction) {
        this.onClickFunction = onClickFunction;
        return this;
    }

    public String asString() {
        String collectedContent = contentList.stream()
                .filter(Objects::nonNull)
                .map(HtmlValue::asString)
                .collect(joining());

        return String.format("<div%s%s>%s</div>",
                className == null ? "" : String.format(" class=\"%s\"", className),
                onClickFunction == null ? "" : String.format(" onClick=\"%s\"", onClickFunction),
                collectedContent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivElement that = (DivElement) o;
        return Objects.equals(contentList, that.contentList) &&
                Objects.equals(className, that.className) &&
                Objects.equals(onClickFunction, that.onClickFunction);
    }

    public boolean isNotEmpty() {
        return !contentList.isEmpty();
    }
}