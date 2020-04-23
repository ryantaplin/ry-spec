package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static extension.report.parser.html.css.CssBuilder.css;
import static extension.report.parser.html.css.CssColour.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class CssBuilderTest {

    private static final String WHITE_HEX = "#FFFFFF";

    @Test
    void multiStyleResultAsString() {
        CssBuilder result = css()
                .fontColour(WHITE)
                .backgroundColour(WHITE);
        assertThat(result.asString()).isEqualTo(String.format("style=\"color:%s; background-color:%s\"", WHITE_HEX, WHITE_HEX));
    }

    @Test
    void backgroundColourAsString() {
        CssBuilder result = css().backgroundColour(WHITE);
        assertThat(result.asString()).isEqualTo(String.format("style=\"background-color:%s\"", WHITE_HEX));
    }

    @Test
    void fontColourAsString() {
        CssBuilder result = css().fontColour(WHITE);
        assertThat(result.asString()).isEqualTo(String.format("style=\"color:%s\"", WHITE_HEX));
    }

    @Test
    void fontSizeAsString() {
        CssBuilder result = css().fontSize(50);
        assertThat(result.asString()).isEqualTo("style=\"font-size:50px\"");
    }

    @Test
    void marginAsString() {
        CssBuilder result = css().margin(10, 10, 10, 10);
        assertThat(result.asString()).isEqualTo("style=\"margin:10px 10px 10px 10px\"");
    }

    @Test
    void paddingAsString() {
        CssBuilder result = css().padding(10, 10, 10, 10);
        assertThat(result.asString()).isEqualTo("style=\"padding:10px 10px 10px 10px\"");
    }

    @Test
    void borderAsString() {
        CssBuilder result = css().border(1);
        assertThat(result.asString()).isEqualTo("style=\"border:1px solid black\"");
    }

    @ParameterizedTest(name = "\"{0}\" should be formatted as \"{1}\"")
    @MethodSource("inputAndOutputForBorderPosition")
    void borderPositionAsStringTest(CssPosition positionInput, String attributeOutput) {
        CssBuilder result = css().border(1, positionInput);
        assertThat(result.asString())
                .isEqualTo(String.format("style=\"%s:1px solid black\"", attributeOutput));
    }
    private static Stream<Arguments> inputAndOutputForBorderPosition() {
        return Stream.of(
                Arguments.of(null, "border"),
                Arguments.of(CssPosition.TOP, "border-top"),
                Arguments.of(CssPosition.LEFT, "border-left"),
                Arguments.of(CssPosition.BOTTOM, "border-bottom"),
                Arguments.of(CssPosition.RIGHT, "border-right"));
    }
}