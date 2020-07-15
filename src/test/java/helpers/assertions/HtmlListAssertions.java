package helpers.assertions;

import extension.report.parser.html.HtmlValue;
import org.assertj.core.api.ListAssert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class HtmlListAssertions {

    private final List<HtmlValue> result;

    private HtmlListAssertions(List<HtmlValue> result) {
        this.result = result;
    }

    public static HtmlListAssertions assertThatHtml(List<HtmlValue> value) {
        return new HtmlListAssertions(value);
    }

    public ListAssert<HtmlValue> containsExactly(HtmlValue... expected) {
        return assertThat(result)
                .describedAs(prettyErrorMessage(expected))
                .containsExactly(expected);
    }

    public void isEmpty() {
        String actualResult = result.stream().map(HtmlValue::asString).collect(Collectors.joining());
        assertThat(result)
                .describedAs(format("\nExpected html content to be empty but found:\n\n%s", actualResult))
                .isEmpty();
    }

    private String prettyErrorMessage(HtmlValue... expected) {
        return format("\n\nExpected html content to match\n\n expected:\n%s\n\n actual:\n%s\n\n",
                getPrettyString(expected),
                getPrettyString(result)
        );
    }

    private String getPrettyString(HtmlValue... elements) {
        return getPrettyString(Arrays.asList(elements));
    }

    private String getPrettyString(List<HtmlValue> elements) {
        return elements.stream().map(HtmlValue::asString).collect(Collectors.joining("\n"));
    }
}
