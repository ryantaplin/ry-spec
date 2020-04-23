package extension.report.parser.helper;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CamelCaseSplitterTest {

    private final CamelCaseSplitter camelCaseSplitter = new CamelCaseSplitter();

    @Test
    void allCamelCasedWordsAreSeparateByASpace() {
        assertThat(camelCaseSplitter.split("theQuick")).contains("the Quick");
    }

    @Test
    void doesNotReturnStringWithDoubleSpaces() {
        assertThat(camelCaseSplitter.split("theQuick BrownFox")).isEqualTo("the Quick Brown Fox");
    }
}