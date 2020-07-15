package extension.report.parser.helper;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CamelCaseSplitter {

    public static final String CAMEL_CASE_REGEX = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";

    public String split(String unformattedStrings) {
        return Optional.ofNullable(unformattedStrings)
                .map(x -> x.split(CAMEL_CASE_REGEX)).stream().flatMap(Stream::of)
                .collect(Collectors.joining(" "));
    }

}