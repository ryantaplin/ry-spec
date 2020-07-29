package extension.internal.report.parser.helper;

import extension.internal.domain.test.method.MethodSourceCode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SourceCodeParserTest {

    public static final MethodSourceCode INPUT_SOURCE_CODE_EXAMPLE_1 = new MethodSourceCode("somethingFail() {\n" +
            "                Assertions.fail();\n" +
            "                }");

    public static final MethodSourceCode INPUT_SOURCE_CODE_EXAMPLE_2 = new MethodSourceCode("somethingFail(String input) {\n" +
            "                doSomething(inputOne, inputTwo);\n" +
            "                Assertions.fail();\n" +
            "                }");

    public static final String OUTPUT_SOURCE_CODE_EXAMPLE_2 = "Do something input one input two\n" +
            "Assertions fail\n";

    private final SentenceFormatter sentenceFormatter = new SentenceFormatter(new CamelCaseSplitter());
    private final ForbiddenCharacterFilter forbiddenCharacterFilter = new ForbiddenCharacterFilter();

    private final SourceCodeParser sourceCodeParser = new SourceCodeParser(forbiddenCharacterFilter, sentenceFormatter);

    //TODO: Replace/Remove enum qualifiers? i.e 'Enum.SOMETHING' -> SOMETHING
    //TODO: Replace/Remove key words? i.e 'new Something()' -> Something
    //TODO: Replace/Remove static qualifiers? i.e 'Optional.of(Something)' -> 'of ...' or just '...'

    @Test
    void replacesSyntaxWithSpace() {
        assertThat(sourceCodeParser.format(INPUT_SOURCE_CODE_EXAMPLE_2))
                .isEqualTo(OUTPUT_SOURCE_CODE_EXAMPLE_2);
    }

    @Test
    void removesMethodName() {
        assertThat(sourceCodeParser.format(INPUT_SOURCE_CODE_EXAMPLE_1))
                .doesNotContain("somethingFail()");
    }

    @Test
    void removesMethodNameWithParameters() {
        assertThat(sourceCodeParser.format(INPUT_SOURCE_CODE_EXAMPLE_2))
                .doesNotContain("somethingFail(String input)");
    }


}