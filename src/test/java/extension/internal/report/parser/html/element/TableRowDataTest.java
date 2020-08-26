package extension.internal.report.parser.html.element;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.element.TableRow.*;
import static extension.internal.report.parser.html.element.TableRowData.rowData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TableRowDataTest {

    //TODO: ... i was lazy today

    @Test
    void rowDataAsString() {
        String result = rowData("value").asString();
        assertThat(result).isEqualTo("<td>value</td>");
    }
}