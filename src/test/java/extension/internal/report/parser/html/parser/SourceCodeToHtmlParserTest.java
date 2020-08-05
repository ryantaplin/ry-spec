package extension.internal.report.parser.html.parser;

import extension.internal.report.parser.helper.SourceCodeParser;
import extension.internal.report.parser.html.HtmlValue;
import extension.internal.domain.test.method.MethodSourceCode;
import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;
import static helpers.assertions.HtmlAssertions.assertThatHtml;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SourceCodeToHtmlParserTest {

    private final SourceCodeParser sourceCodeParser = mock(SourceCodeParser.class);
    private final SourceCodeToHtmlParser sourceCodeToHtmlParser = new SourceCodeToHtmlParser(sourceCodeParser);

    public static final String A_STRING = "someValue";
    public static final MethodSourceCode METHOD_SOURCE_CODE = new MethodSourceCode(A_STRING);

    @Test
    void returnsDivWithValueReturnedBySourceCodeParser() {
        when(sourceCodeParser.format(METHOD_SOURCE_CODE)).thenReturn(A_STRING);

        HtmlValue parse = sourceCodeToHtmlParser.parse(METHOD_SOURCE_CODE);
        assertThatHtml(parse).isEqualTo(div(content(A_STRING)).withClassName("sourceCode"));
    }
}