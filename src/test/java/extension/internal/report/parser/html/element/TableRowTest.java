package extension.internal.report.parser.html.element;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.element.TableRow.row;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TableRowTest {

    //TODO: ... i was lazy today

    @Test
    void rowAsString() {
        String result = row().asString();
        assertThat(result).isEqualTo("<tr></tr>");
    }
}