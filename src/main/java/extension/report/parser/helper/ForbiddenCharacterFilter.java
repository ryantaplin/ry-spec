package extension.report.parser.helper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ForbiddenCharacterFilter {

    private static final List<Character> forbiddenCharacters = Arrays.asList(',', ';', '{', '}', '(', ')', '.', '_');

    public String filter(String input) {
        return Optional.ofNullable(input)
                .map(this::removeForbiddenCharactersFromString)
                .orElse("");
    }

    private String removeForbiddenCharactersFromString(String string) {
        return Optional.ofNullable(string).stream()
                .flatMapToInt(String::codePoints)
                .mapToObj(integer -> (char) integer)
                .map(this::forbiddenCharacterToSpace)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private Character forbiddenCharacterToSpace(Character c) {
        if (forbiddenCharacters.contains(c)) {
            return ' ';
        }
        return c;
    }
}
