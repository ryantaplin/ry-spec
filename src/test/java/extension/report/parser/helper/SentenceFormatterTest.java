package extension.report.parser.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SentenceFormatterTest {

    private final SentenceFormatter sentenceFormatter = new SentenceFormatter();

    @Test
    void firstCharacterOfInputIsCapitalised() {
        assertThat(sentenceFormatter.format("theQuick"))
                .startsWith("Thequick");
    }

    @ParameterizedTest(name = "\"{0}\" should be formatted as \"{1}\"")
    @MethodSource("inputAndOutputExamples")
    void allCharactersFollowingTheFirstLetterOfTheFirstWordAreNotCapitalised(String input, String output) {
        assertThat(sentenceFormatter.format(input))
                .isEqualTo(output);
    }

    @Test
    void doesNotBlowUpWhenEmptyStringIsInput() {
        assertThat(sentenceFormatter.format(""))
                .isEqualTo("");
    }

    private static Stream<Arguments> inputAndOutputExamples() {
        return Stream.of(
                Arguments.of("THE QUICK BROWN FOX", "The quick brown fox"),
                Arguments.of("the quick brown fox", "The quick brown fox"));
    }

}