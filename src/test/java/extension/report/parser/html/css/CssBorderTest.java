package extension.report.parser.html.css;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static extension.report.parser.html.css.CssBorder.border;
import static extension.report.parser.html.css.CssElements.css;
import static org.assertj.core.api.Assertions.assertThat;

class CssBorderTest {

    @Test
    void borderAsString() {
        CssElements result = css(border(1));
        assertThat(result.asString()).isEqualTo("style=\"border:1px solid black\"");
    }

    @ParameterizedTest(name = "\"{0}\" should be formatted as \"{1}\"")
    @MethodSource("inputAndOutputForBorderPosition")
    void borderPositionAsStringTest(CssPosition positionInput, String attributeOutput) {
        CssElements result = css(border(1, positionInput));
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