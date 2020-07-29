package helpers.assertions;

import extension.internal.report.parser.html.HtmlValue;
import org.assertj.core.api.ObjectAssert;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class HtmlAssertions {

    private final HtmlValue result;

    private HtmlAssertions(HtmlValue result) {
        this.result = result;
    }

    public static HtmlAssertions assertThatHtml(HtmlValue value) {
        return new HtmlAssertions(value);
    }

    public ObjectAssert<HtmlValue> isEqualTo(HtmlValue expected) {
        return assertThat(result)
                .describedAs(prettyErrorMessage(expected))
                .isEqualTo(expected);
    }

    private String prettyErrorMessage(HtmlValue expected) {
        return format("\n\nExpected html content to match\n\nExpected:\n%s\n\nActual:\n%s\n\n",
                expected.asString(), result.asString()
        );
    }
}
