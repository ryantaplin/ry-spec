package extension.report.parser.helper;

import java.util.Arrays;

import static java.lang.Character.toUpperCase;
import static java.util.stream.Collectors.joining;

public class CamelCaseParser {

    public static final String CAMEL_CASE_REGEX = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";

    public String parse(String unformattedString) {
        String spaceSeparatedSentence = Arrays.stream(unformattedString.split(CAMEL_CASE_REGEX))
                .map(String::toLowerCase)
                .collect(joining(" "));

        if (spaceSeparatedSentence.isEmpty()) return "";
        else return (toUpperCase(spaceSeparatedSentence.charAt(0)) + spaceSeparatedSentence.substring(1)).replaceAll(" {2}", " ");
    }

}