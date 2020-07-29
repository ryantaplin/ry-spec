package extension.internal.report.parser.html;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.HtmlContent.content;
import static org.assertj.core.api.Assertions.assertThat;

class HtmlContentTest {

    @Test
    void replaceAllNewLinesWithHtmlLineBreaks() {
        assertThat(content("Some\nNewLine\n").asString()).isEqualTo("Some<br>NewLine<br>");
    }

    @Test
    void htmlContentAsStringReturnsInputValue() {
        assertThat(content("someValue").asString()).isEqualTo("someValue");
    }

    @Test
    void htmlContentIsEqualWhenInputValueIsSame() {
        assertThat(content("one")).isEqualTo(content("one"));
    }

    @Test
    void htmlContentIsNotEqualWhenInputValueIsDifferent() {
        assertThat(content("one")).isNotEqualTo(content("two"));
    }
}