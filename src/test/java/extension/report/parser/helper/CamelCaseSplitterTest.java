package extension.report.parser.helper;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CamelCaseSplitterTest {

    private final CamelCaseSplitter camelCaseSplitter = new CamelCaseSplitter();

    @Test
    void allCamelCasedWordsAreSeparateByASpace() {
        assertThat(camelCaseSplitter.split("someSentence")).contains("some Sentence");
    }

    @Test
    void removesLeadingSpaces() {
        assertThat(camelCaseSplitter.split("   someSentence")).isEqualTo("some Sentence");
    }

    @Test
    void removesTrailingSpaces() {
        assertThat(camelCaseSplitter.split("someSentence   ")).isEqualTo("some Sentence");
    }

    @Test
    void removesMultipleSpacesBetweenWords() {
        assertThat(camelCaseSplitter.split("some   Very LongUnformattedSentence")).isEqualTo("some Very Long Unformatted Sentence");
    }

    @Test
    void doesNotThrowExceptionWhenNullIsInput() {
        assertThat(camelCaseSplitter.split(null)).isEqualTo("");
    }
}