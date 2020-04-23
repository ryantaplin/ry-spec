package extension.report.parser;

import extension.report.parser.helper.CamelCaseSplitter;
import extension.report.parser.helper.SentenceFormatter;
import extension.report.parser.helper.SourceCodeParser;
import extension.report.parser.html.HtmlValue;
import extension.test.TestMethodData;
import extension.test.TestMethodSourceCode;
import extension.test.TestResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestSourceCodeToHtmlParserTest {

    private final SourceCodeParser sourceCodeParser = mock(SourceCodeParser.class);
    private final CamelCaseSplitter camelCaseSplitter = mock(CamelCaseSplitter.class);
    private final SentenceFormatter sentenceFormatter = mock(SentenceFormatter.class);

    private final TestSourceCodeToHtmlParser parser = new TestSourceCodeToHtmlParser(sourceCodeParser, camelCaseSplitter, sentenceFormatter);

    @BeforeEach
    void setUp() {
        final Answer<String> withInput = invocation -> {
            Object[] args = invocation.getArguments();
            return (String) args[0];
        };
        when(camelCaseSplitter.split(anyString())).thenAnswer(withInput);
        when(sourceCodeParser.parse(anyString())).thenAnswer(withInput);
        when(sentenceFormatter.format(anyString())).thenAnswer(withInput);
    }

    @Test
    void returnsEmptyListWhenInputIsEmpty() {
        List<HtmlValue> result = parser.parse(Collections.emptyList());
        assertThat(result).isEmpty();
    }

    @Test
    void returnsDivElementWithExpectedContent() {
        List<HtmlValue> result = parser.parse(Arrays.asList(testMethodData1()));
        assertThat(result).containsExactly(div(div(content("someName : PASSED")), div(content("sourceCode"))));
    }

    @Test
    void returnsMultipleDivElementsWithExpectedContent() {
        List<HtmlValue> result = parser.parse(Arrays.asList(testMethodData1(), testMethodData2()));
        assertThat(result).containsExactly(
                div(div(content("someName : PASSED")), div(content("sourceCode"))),
                div(div(content("anotherName : FAILED")), div(content("sourceCode"))));
    }

    private TestMethodData testMethodData1() {
        return new TestMethodData("someName", new TestMethodSourceCode("sourceCode"), TestResult.PASSED);
    }

    private TestMethodData testMethodData2() {
        return new TestMethodData("anotherName", new TestMethodSourceCode("sourceCode"), TestResult.FAILED);
    }

}