package extension.report.parser.helper;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CamelCaseSplitterTest {

    private final CamelCaseSplitter camelCaseSplitter = new CamelCaseSplitter();

    @Test
    void stringWithNoCamelCaseIsNotTransformed() {
        assertThat(camelCaseSplitter.split("somesentence")).isEqualTo("somesentence");
    }

    @Test
    void allCamelCasedWordsAreSeparateByASpace() {
        assertThat(camelCaseSplitter.split("someSentence")).contains("some Sentence");
    }

    @Test
    void doesNotThrowExceptionWhenNullIsInput() {
        assertThat(camelCaseSplitter.split(null)).isEqualTo("");
    }

    @Test
    void doesNotThrowExceptionWhenEmptyStringIsInput() {
        assertThat(camelCaseSplitter.split("")).isEqualTo("");
    }
}