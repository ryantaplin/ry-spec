package extension.report.parser.helper;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CamelCaseParserTest {

    private final CamelCaseParser camelCaseParser = new CamelCaseParser();

    @Test
    void firstLetterOfCamelCaseIsCapitalised() {
        assertThat(camelCaseParser.parse("theQuick")).startsWith("The");
    }

    @Test
    void allCamelCasedWordsAreSeparateByASpace() {
        assertThat(camelCaseParser.parse("theQuick")).contains("quick");
    }

    @Test
    void firstLetterOfAllWordsFollowingTheFirstWordAreNotCapitalised() {
        assertThat(camelCaseParser.parse("theQuickBrownFox")).isEqualTo("The quick brown fox");
    }

    @Test
    void doesNotPrintDoubleSpaces() {
        assertThat(camelCaseParser.parse("theQuick BrownFox")).isEqualTo("The quick brown fox");
    }
}