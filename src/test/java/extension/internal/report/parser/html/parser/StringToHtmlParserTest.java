package extension.internal.report.parser.html.parser;

import extension.internal.report.parser.helper.SentenceFormatter;
import extension.internal.report.parser.html.HtmlValue;
import extension.internal.report.parser.html.css.helper.TestContentCssHelper;
import extension.internal.domain.test.method.Result;
import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;
import static helpers.assertions.HtmlAssertions.assertThatHtml;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StringToHtmlParserTest {


    private SentenceFormatter sentenceFormatter = mock(SentenceFormatter.class);
    private TestContentCssHelper cssHelper = mock(TestContentCssHelper.class);
    private final StringToHtmlHeaderParser stringToHtmlHeaderParser = new StringToHtmlHeaderParser(
            sentenceFormatter,
            cssHelper
    );

    @Test
    void returnsDivWithNameAndResultSeparatedByColon() {
        when(sentenceFormatter.format("name")).thenReturn("name");

        HtmlValue result = stringToHtmlHeaderParser.parse("name", Result.PASSED);
        assertThatHtml(result).isEqualTo(div(content("name : PASSED")));
    }
}