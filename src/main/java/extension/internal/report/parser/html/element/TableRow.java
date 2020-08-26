package extension.internal.report.parser.html.element;

import extension.internal.report.parser.html.HtmlValue;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class TableRow implements HtmlElement {

    private final List<? extends TableRowData> contentList;
    private String className;
    private String onClickFunction;

    public TableRow(List<? extends TableRowData> contentList) {
        this.contentList = contentList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static TableRow row(TableRowData... content) {
        return new TableRow(Arrays.asList(content));
    }

    public static TableRow row(List<? extends TableRowData> content) {
        return new TableRow(content);
    }

    @Override
    public TableRow withClassName(String className) {
        this.className = className;
        return this;
    }

    @Override
    public TableRow withOnClickFunction(String onClickFunction) {
        this.onClickFunction = onClickFunction;
        return this;
    }

    public String asString() {
        String collectedContent = contentList.stream()
                .filter(Objects::nonNull)
                .map(HtmlValue::asString)
                .collect(joining());

        return String.format("<tr%s%s>%s</tr>",
                className == null ? "" : String.format(" class=\"%s\"", className),
                onClickFunction == null ? "" : String.format(" onClick=\"%s\"", onClickFunction),
                collectedContent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableRow that = (TableRow) o;
        return Objects.equals(contentList, that.contentList) &&
                Objects.equals(className, that.className) &&
                Objects.equals(onClickFunction, that.onClickFunction);
    }

    public boolean isNotEmpty() {
        return !contentList.isEmpty();
    }
}