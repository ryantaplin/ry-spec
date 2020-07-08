package extension.report.parser.helper;

import org.junit.platform.commons.util.StringUtils;

import java.util.Optional;
import java.util.function.Function;

import static com.google.common.base.Ascii.toUpperCase;

public class SentenceFormatter {

    public String format(String value) {
        return Optional.ofNullable(value)
                .filter(StringUtils::isNotBlank)
                .map(formatString())
                .orElse("");
    }

    private Function<String, String> formatString() {
        return value -> String.format("%s%s",
                toUpperCase(value.charAt(0)),
                value.substring(1).toLowerCase()
        );
    }
}
