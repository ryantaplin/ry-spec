package extension.internal.report.parser.html.element;

import extension.internal.report.parser.html.HtmlValue;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class TableElement implements HtmlElement {

    private final List<? extends TableRow> contentList;
    private String className;
    private String onClickFunction;

    public TableElement(List<? extends TableRow> listOfRows) {
        this.contentList = listOfRows.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static TableElement table(TableRow... row) {
        return new TableElement(Arrays.asList(row));
    }

    public static TableElement table(List<? extends TableRow> listOfRows) {
        return new TableElement(listOfRows);
    }

    @Override
    public TableElement withClassName(String className) {
        this.className = className;
        return this;
    }

    @Override
    public TableElement withOnClickFunction(String onClickFunction) {
        this.onClickFunction = onClickFunction;
        return this;
    }

    public String asString() {
        String collectedContent = contentList.stream()
                .filter(Objects::nonNull)
                .map(HtmlValue::asString)
                .collect(joining());

        return String.format("<table%s%s>%s</table>",
                className == null ? "" : String.format(" class=\"%s\"", className),
                onClickFunction == null ? "" : String.format(" onClick=\"%s\"", onClickFunction),
                collectedContent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableElement that = (TableElement) o;
        return Objects.equals(contentList, that.contentList) &&
                Objects.equals(className, that.className) &&
                Objects.equals(onClickFunction, that.onClickFunction);
    }

    public boolean isNotEmpty() {
        return !contentList.isEmpty();
    }
}