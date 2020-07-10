package extension.report.parser;

import extension.report.parser.helper.CamelCaseSplitter;
import extension.report.parser.helper.SentenceFormatter;
import extension.report.parser.helper.SourceCodeParser;
import extension.report.parser.html.HtmlValue;
import extension.report.parser.html.css.helper.TestContentCssHelper;
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

class TestMethodDataToHtmlParserTest {

    private final SourceCodeParser sourceCodeParser = mock(SourceCodeParser.class);
    private final CamelCaseSplitter camelCaseSplitter = mock(CamelCaseSplitter.class);
    private final SentenceFormatter sentenceFormatter = mock(SentenceFormatter.class);
    private final TestContentCssHelper testContentCssHelper = mock(TestContentCssHelper.class);


    private final TestMethodDataToHtmlParser parser = new TestMethodDataToHtmlParser(
            sourceCodeParser,
            camelCaseSplitter,
            sentenceFormatter,
            testContentCssHelper);

    @BeforeEach
    void setUp() {
        final Answer<String> withInput = invocation -> {
            Object[] args = invocation.getArguments();
            return (String) args[0];
        };
        when(camelCaseSplitter.split(anyString())).thenAnswer(withInput);
        when(sourceCodeParser.parse(anyString())).thenAnswer(withInput);
        when(sentenceFormatter.format(anyString())).thenAnswer(withInput);
        when(testContentCssHelper.bodyCss()).thenReturn(null);
        when(testContentCssHelper.headerCss()).thenReturn(null);
        when(testContentCssHelper.containerCss()).thenReturn(null);
    }

    @Test
    void returnsEmptyListWhenInputIsEmpty() {
        List<HtmlValue> result = parser.parse(Collections.emptyList());
        assertThat(result).isEmpty();
    }

    @Test
    void returnsDivElementWithExpectedContent() {
        List<HtmlValue> result = parser.parse(Arrays.asList(testMethodData1()));
        assertThat(result).containsExactlyInAnyOrder(div(div(content("someName : PASSED")), div(content("sourceCode")), div()));
    }

    @Test
    void returnsMultipleDivElementsWithExpectedContent() {
        List<HtmlValue> result = parser.parse(Arrays.asList(testMethodData1(), testMethodData2()));
        assertThat(result).containsExactlyInAnyOrder(
                div(div(content("someName : PASSED")), div(content("sourceCode")), div()),
                div(div(content("anotherName : FAILED")), div(content("sourceCode")), div()));
    }

    private TestMethodData testMethodData1() {
        return new TestMethodData("someName", new TestMethodSourceCode("sourceCode"), TestResult.PASSED);
    }

    private TestMethodData testMethodData2() {
        return new TestMethodData("anotherName", new TestMethodSourceCode("sourceCode"), TestResult.FAILED);
    }

}