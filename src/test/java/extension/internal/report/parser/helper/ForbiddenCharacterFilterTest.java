package extension.internal.report.parser.helper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ForbiddenCharacterFilterTest {

    private final ForbiddenCharacterFilter forbiddenCharacterFilter = new ForbiddenCharacterFilter();

    @ParameterizedTest(name = "\"{0}\" should not exist in the formatted output")
    @MethodSource("inputAndOutputForForbiddenCharacters")
    void removesForbiddenCharacters(String forbiddenCharacter) {
        assertThat(forbiddenCharacterFilter.filter(sourceCodeWith(forbiddenCharacter)))
                .doesNotContain(forbiddenCharacter);
    }

    private static Stream<Arguments> inputAndOutputForForbiddenCharacters() {
        return Stream.of(",", ";", "{", "}", "(", ")", ".", "_").map(Arguments::of);
    }

    private String sourceCodeWith(String forbiddenCharacter) {
        return String.format("input%s\n with %s", forbiddenCharacter, forbiddenCharacter);
    }

}