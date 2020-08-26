package extension.internal.report.parser.html.element;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.element.TableElement.table;
import static org.assertj.core.api.Assertions.assertThat;

class TableElementTest {

    //TODO: ... i was lazy today

    @Test
    void tableAsString() {
        String result = table().asString();
        assertThat(result).isEqualTo("<table></table>");
    }
}