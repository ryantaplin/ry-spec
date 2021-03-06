package extension.internal.report.parser.html.css.attribute;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static extension.internal.report.parser.html.css.attribute.CssBorder.border;
import static org.assertj.core.api.Assertions.assertThat;

class CssBorderTest {

    @Test
    void borderAsString() {
        String result = border(1).getAttributeWithValue();
        assertThat(result).isEqualTo("border:1px solid black");
    }

    @ParameterizedTest(name = "\"{0}\" should be formatted as \"{1}\"")
    @MethodSource("inputAndOutputForBorderPosition")
    void borderPositionAsStringTest(CssPosition positionInput, String attributeOutput) {
        String result = border(1, positionInput).getAttributeWithValue();
        assertThat(result)
                .isEqualTo(String.format("%s:1px solid black", attributeOutput));
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