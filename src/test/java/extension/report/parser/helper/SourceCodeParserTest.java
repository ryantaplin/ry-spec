package extension.report.parser.helper;

import extension.report.parser.html.css.CssPosition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class sourceCodeParserTest {

    public static final String INPUT_SOURCE_CODE_EXAMPLE_1 = "somethingFail() {\n" +
            "                Assertions.fail();\n" +
            "                }";

    public static final String INPUT_SOURCE_CODE_EXAMPLE_2 = "somethingFail(String input) {\n" +
            "                doSomething(inputOne, inputTwo);\n" +
            "                Assertions.fail();\n" +
            "                }";

    public static final String OUTPUT_SOURCE_CODE_EXAMPLE_2 = "doSomething inputOne inputTwo\n" +
            "Assertions fail\n";

    private final SourceCodeParser sourceCodeParser = new SourceCodeParser();

    @Test
    void replacesSyntaxWithSpace() {
        assertThat(sourceCodeParser.parse(INPUT_SOURCE_CODE_EXAMPLE_2))
                .isEqualTo(OUTPUT_SOURCE_CODE_EXAMPLE_2);
    }

    @Test
    void removesMethodName() {
        assertThat(sourceCodeParser.parse(INPUT_SOURCE_CODE_EXAMPLE_1))
                .doesNotContain("somethingFail()");
    }

    @Test
    void removesMethodNameWithParameters() {
        assertThat(sourceCodeParser.parse(INPUT_SOURCE_CODE_EXAMPLE_2))
                .doesNotContain("somethingFail(String input)");
    }

    @ParameterizedTest(name = "\"{0}\" should not exist in the formatted output")
    @MethodSource("inputAndOutputForForbiddenCharacters")
    void removesForbiddenCharacters(String forbiddenCharacter) {
        assertThat(sourceCodeParser.parse(sourceCodeWith(forbiddenCharacter)))
                .doesNotContain(forbiddenCharacter);
    }

    private static Stream<Arguments> inputAndOutputForForbiddenCharacters() {
        return Stream.of(",", ";", "{", "}", "(", ")", ".", " ", "_").map(Arguments::of);
    }

    private String sourceCodeWith(String forbiddenCharacter) {
        return String.format("somethingFail(String input) {\n" +
                "%s;\n", forbiddenCharacter);
    }
}