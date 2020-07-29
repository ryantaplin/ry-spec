package extension.internal.report.parser.helper;

import extension.internal.domain.test.method.MethodSourceCode;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class SourceCodeParser {

    private final ForbiddenCharacterFilter forbiddenCharacterFilter;
    private final SentenceFormatter formatter;

    public SourceCodeParser(ForbiddenCharacterFilter forbiddenCharacterFilter, SentenceFormatter formatter) {
        this.forbiddenCharacterFilter = forbiddenCharacterFilter;
        this.formatter = formatter;
    }

    public String format(MethodSourceCode input) {
        return Optional.ofNullable(input)
                .map(MethodSourceCode::asString)
                .map(string -> Arrays.asList(string.split("\n"))).orElse(emptyList()).stream()
                .skip(1)
                .map(forbiddenCharacterFilter::filter)
                .map(formatter::format)
                .collect(Collectors.joining("\n"));
    }


}

