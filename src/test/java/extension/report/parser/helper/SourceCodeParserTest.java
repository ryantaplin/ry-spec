package extension.report.parser.helper;

import org.junit.jupiter.api.Test;

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

    @Test
    void removesCurlyBraces() {
        assertThat(sourceCodeParser.parse(INPUT_SOURCE_CODE_EXAMPLE_1))
                .doesNotContain("{").doesNotContain("}");
    }

    @Test
    void removesRoundBraces() {
        assertThat(sourceCodeParser.parse(INPUT_SOURCE_CODE_EXAMPLE_1))
                .doesNotContain("(").doesNotContain(")");
    }

    @Test
    void removesSemiColons() {
        assertThat(sourceCodeParser.parse(INPUT_SOURCE_CODE_EXAMPLE_1))
                .doesNotContain(";");
    }

    @Test
    void removesCommas() {
        assertThat(sourceCodeParser.parse(INPUT_SOURCE_CODE_EXAMPLE_2))
                .doesNotContain(",");
    }

    @Test
    void removesFullStops() {
        assertThat(sourceCodeParser.parse(INPUT_SOURCE_CODE_EXAMPLE_1))
                .doesNotContain(".");
    }
}