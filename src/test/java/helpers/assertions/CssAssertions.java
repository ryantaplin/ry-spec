package helpers.assertions;

import extension.internal.report.parser.html.css.CssElements;
import extension.internal.report.parser.html.css.CssElement;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class CssAssertions {

    private final CssElements result;

    private CssAssertions(CssElements result) {
        this.result = result;
    }

    public static CssAssertions assertThatCss(CssElements value) {
        return new CssAssertions(value);
    }

    public ListAssert<CssElement> containsExactlyInAnyOrder(CssElement... expected) {
        try {
            List<CssElement> actualElements = getActualCssElements();
            return assertThat(actualElements)
                    .describedAs(prettyErrorMessage(actualElements, expected))
                    .containsExactlyInAnyOrder(expected);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return Assertions.fail("Could not access css elements.");
        }
    }

    private List<CssElement> getActualCssElements() throws NoSuchFieldException, IllegalAccessException {
        Field cssElementsField = result.getClass().getDeclaredField("cssElements");
        cssElementsField.setAccessible(true);
        return (List<CssElement>) cssElementsField.get(result);
    }

    private String prettyErrorMessage(List<CssElement> actualElements, CssElement[] expected) {
        return format("\n\nExpected css content to match\n\nExpected:\n%s\n\nActual:\n%s\n\n",
                getPrettyString(expected),
                getPrettyString(actualElements)
        );
    }

    private String getPrettyString(CssElement... elements) {
        return getPrettyString(Arrays.asList(elements));
    }

    private String getPrettyString(List<CssElement> elements) {
        return elements.stream().map(CssElement::getAttributeWithValue).collect(Collectors.joining("\n"));
    }
}
