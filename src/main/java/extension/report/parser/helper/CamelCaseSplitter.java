package extension.report.parser.helper;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CamelCaseSplitter {

    public static final String CAMEL_CASE_REGEX = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";

    public String split(String unformattedString) {
        return Optional.ofNullable(unformattedString)
                .map(x -> x.split(CAMEL_CASE_REGEX))
                .stream().flatMap(Stream::of)
                .map(String::trim)
                .collect(Collectors.joining(" "));
    }

}