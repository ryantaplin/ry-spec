package extension.internal.report.parser.html.element;

import extension.internal.report.parser.html.HtmlValue;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TableRowData implements HtmlValue {

    private final String value;
    private List<String> classNames;

    private TableRowData(String value) {
        this.value = value;
    }

    public static TableRowData rowData(String value) {
        return new TableRowData(value);
    }

    public TableRowData withClassName(String className) {
        this.classNames = List.of(className);
        return this;
    }

    public TableRowData withClassNames(String... classNames) {
        this.classNames = Arrays.asList(classNames);
        return this;
    }

    public String asString() {
        return String.format("<td%s>%s</td>",
                classNames == null ? "" : String.format(" class=\"%s\"", String.join(" ", classNames)),
                value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableRowData that = (TableRowData) o;
        return Objects.equals(value, that.value);
    }
}