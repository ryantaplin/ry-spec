package extension.report.parser.helper;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public class CamelCaseSplitter {

    public static final String CAMEL_CASE_REGEX = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";

    public String split(String unformattedString) {
        String spaceSeparatedSentence = Arrays.stream(unformattedString.split(CAMEL_CASE_REGEX))
                .collect(joining(" "));

        if (spaceSeparatedSentence.isEmpty()) return "";
        else return spaceSeparatedSentence.replaceAll(" {2}", " ");
    }

}