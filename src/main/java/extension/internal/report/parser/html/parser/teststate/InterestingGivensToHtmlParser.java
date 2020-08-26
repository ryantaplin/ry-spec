package extension.internal.report.parser.html.parser.teststate;

import extension.TestState;
import extension.internal.report.parser.html.element.HtmlElement;
import extension.internal.report.parser.html.element.TableRow;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;
import static extension.internal.report.parser.html.element.TableElement.table;
import static extension.internal.report.parser.html.element.TableRow.row;
import static extension.internal.report.parser.html.element.TableRowData.rowData;

public class InterestingGivensToHtmlParser {

    public HtmlElement parse(TestState testState) {
        return Optional.ofNullable(testState)
                .map(TestState::getInterestingGivenEntries)
                .flatMap(this::parseInterestingGivens)
                .map(element -> element.withClassName("interestingGivenContainer"))
                .orElse(null);

    }

    private Optional<HtmlElement> parseInterestingGivens(List<Map.Entry<String, List<Object>>> entries) {
        List<TableRow> interestingGivenRows = entries.stream()
                .map(this::toKeyValuesMapping)
                .collect(Collectors.toList());

        if (!interestingGivenRows.isEmpty()) {
            return Optional.of(
                    div(
                            div(content("Interesting Givens")).withClassName("interestingGivensHeader"),
                            table(interestingGivenRows).withClassName("tableEntity")
                    )
            );
        }
        return Optional.empty();
    }

    private TableRow toKeyValuesMapping(Map.Entry<String, List<Object>> entry) {
        return row(
                rowData(entry.getKey()).withClassNames("tableEntity", "tableHeaderEntity"),
                rowData(joinedValues(entry)).withClassNames("tableEntity", "tableValueEntity")
        ).withClassName("tableEntity");
    }

    private String joinedValues(Map.Entry<String, List<Object>> entry) {
        List<String> res = entry.getValue().stream()
                .map(Object::toString)
                .collect(Collectors.toList()); //TODO: custom object parsing;

        if (res.size() > 1) {
            return String.format("[%s]", String.join(", ", res));
        }
        return Optional.ofNullable(res.get(0)).orElse("");
    }
}