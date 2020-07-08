package extension.report.parser.helper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class SourceCodeParser {

    private final List<Character> forbiddenCharacters = Arrays.asList(',', ';', '{', '}', '(', ')', '.', ' ', '_');

    public String parse(String input) {
        return Stream.of(removeForbiddenCharacters(input).split("\n"))
                .skip(1)
                .map(line -> line.replaceAll(" +", " "))
                .map(String::trim)
                .collect(Collectors.joining("\n"));
    }

    private String removeForbiddenCharacters(String input) {
        return input.codePoints()
                .mapToObj(character -> (char)character)
                .map(this::forbiddenCharacterToSpace)
                .map(String::valueOf)
                .collect(joining());
    }

    private Character forbiddenCharacterToSpace(Character character) {
        if (forbiddenCharacters.contains(character)) return ' ';
        else return character;
    }
}

