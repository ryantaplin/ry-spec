package extension.report.parser.helper;

import org.junit.platform.commons.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.google.common.base.Ascii.toUpperCase;

public class SentenceFormatter {

    private CamelCaseSplitter camelCaseSplitter;

    public SentenceFormatter(CamelCaseSplitter camelCaseSplitter) {
        this.camelCaseSplitter = camelCaseSplitter;
    }

    public String format(String value) {
        return Optional.ofNullable(value)
                .filter(StringUtils::isNotBlank)
                .map(camelCaseSplitter::split)
                .map(this::cleanUpAdditionalWhiteSpace)
                .map(this::sentenceFormat)
                .orElse("");
    }

    private String sentenceFormat(String string) {
        return String.format("%s%s", toUpperCase(string.charAt(0)), string.substring(1).toLowerCase());
    }

    private String cleanUpAdditionalWhiteSpace(String string) {
        return Optional.ofNullable(string)
                .map(String::trim)
                .map(line -> line.replaceAll(" +", " "))
                .orElse("");
    }
}
