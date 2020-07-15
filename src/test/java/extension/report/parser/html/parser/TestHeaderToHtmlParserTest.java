package extension.report.parser.html.parser;

import extension.report.parser.helper.CamelCaseSplitter;
import extension.report.parser.helper.SentenceFormatter;
import extension.report.parser.html.HtmlValue;
import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.test.TestResult;
import org.junit.jupiter.api.Test;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;
import static helpers.assertions.HtmlAssertions.assertThatHtml;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestHeaderToHtmlParserTest {


    private SentenceFormatter sentenceFormatter = mock(SentenceFormatter.class);
    private TestContentCssHelper cssHelper = mock(TestContentCssHelper.class);
    private final TestHeaderToHtmlParser testHeaderToHtmlParser = new TestHeaderToHtmlParser(
            sentenceFormatter,
            cssHelper
    );

    @Test
    void returnsDivWithNameAndResultSeparatedByColon() {
        when(sentenceFormatter.format("name")).thenReturn("name");

        HtmlValue result = testHeaderToHtmlParser.parse("name", TestResult.PASSED);
        assertThatHtml(result).isEqualTo(div(content("name : PASSED")));
    }
}