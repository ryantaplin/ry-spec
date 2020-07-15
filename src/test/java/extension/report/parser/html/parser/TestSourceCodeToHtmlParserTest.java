package extension.report.parser.html.parser;

import extension.report.parser.helper.SourceCodeParser;
import extension.report.parser.html.HtmlValue;
import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.test.TestMethodSourceCode;
import org.junit.jupiter.api.Test;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;
import static helpers.assertions.HtmlAssertions.assertThatHtml;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestSourceCodeToHtmlParserTest {

    private final SourceCodeParser sourceCodeParser = mock(SourceCodeParser.class);
    private final TestContentCssHelper cssHelper = mock(TestContentCssHelper.class);
    TestSourceCodeToHtmlParser testSourceCodeToHtmlParser = new TestSourceCodeToHtmlParser(
            sourceCodeParser,
            cssHelper
    );

    public static final String A_STRING = "someValue";
    public static final TestMethodSourceCode METHOD_SOURCE_CODE = new TestMethodSourceCode(A_STRING);

    @Test
    void returnsDivWithValueReturnedBySourceCodeParser() {
        when(sourceCodeParser.format(METHOD_SOURCE_CODE)).thenReturn(A_STRING);

        HtmlValue parse = testSourceCodeToHtmlParser.parse(METHOD_SOURCE_CODE);
        assertThatHtml(parse).isEqualTo(div(content(A_STRING)));
    }
}