package extension.report.parser;

import extension.report.parser.html.HtmlValue;
import extension.report.parser.html.css.CssElements;
import extension.report.parser.html.css.CssFontSize;
import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.report.parser.html.parser.TestHeaderToHtmlParser;
import extension.report.parser.html.parser.TestSourceCodeToHtmlParser;
import extension.report.parser.html.parser.TestStateToHtmlParser;
import extension.test.TestMethodData;
import extension.test.TestMethodSourceCode;
import extension.test.TestResult;
import extension.test.state.TestState;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.css.CssFontSize.fontSize;
import static extension.report.parser.html.element.DivElement.div;
import static extension.test.TestMethodData.testMethodData;
import static helpers.assertions.HtmlListAssertions.assertThatHtml;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TestSpecimenToHtmlWorkerTest {

    private final TestStateToHtmlParser testStateToHtmlParser = mock(TestStateToHtmlParser.class);
    private final TestHeaderToHtmlParser testHeaderToHtmlParser = mock(TestHeaderToHtmlParser.class);
    private final TestSourceCodeToHtmlParser testSourceCodeToHtmlParser = mock(TestSourceCodeToHtmlParser.class);

    private final TestContentCssHelper testContentCssHelper = mock(TestContentCssHelper.class);

    private final TestSpecimenToHtmlWorker parser = new TestSpecimenToHtmlWorker(
            testHeaderToHtmlParser,
            testSourceCodeToHtmlParser,
            testStateToHtmlParser,
            testContentCssHelper
    );

    private TestState testState = mock(TestState.class);
    private TestMethodData METHOD_DATA = testMethodData("someName", new TestMethodSourceCode("sourceCode"));

    @Test
    void returnsEmptyListWhenInputIsEmpty() {
        List<HtmlValue> result = parser.parse(Collections.emptyList());
        assertThatHtml(result).isEmpty();
    }

    @Test
    void headerParserIsInvokedWithExpectedContent() {
        parser.parse(List.of(METHOD_DATA));
        verify(testHeaderToHtmlParser).parse("someName", TestResult.NOT_RUN);
    }

    @Test
    void sourceCodeParserIsInvokedWithExpectedContent() {
        parser.parse(List.of(METHOD_DATA));
        verify(testSourceCodeToHtmlParser).parse(new TestMethodSourceCode("sourceCode"));
    }

    @Test
    void stateParserIsInvokedWithNullWhenNotPresent() {
        parser.parse(List.of(METHOD_DATA));
        verify(testStateToHtmlParser).parse(null);
    }

    @Test
    void stateParserIsInvokedWithExpectedContent() {
        parser.parse(List.of(testMethodDataWithSomePopulatedState()));
        verify(testStateToHtmlParser).parse(testState);
    }

    @Test
    void parsersAreInvokedForNAmountOfMethodDataProvided() {
        parser.parse(List.of(METHOD_DATA, METHOD_DATA));
        verify(testHeaderToHtmlParser, times(2)).parse(anyString(), any());
        verify(testSourceCodeToHtmlParser, times(2)).parse(any());
        verify(testStateToHtmlParser, times(2)).parse(any());
    }

    @Test
    void parserReturnsExpectedHtmlElementsForInRelationToMethodsProvided() {
        when(testHeaderToHtmlParser.parse(any(), any())).thenReturn(div(content("header")));
        when(testSourceCodeToHtmlParser.parse(any())).thenReturn(div(content("source code")));
        when(testStateToHtmlParser.parse(any())).thenReturn(div());

        List<HtmlValue> parse = parser.parse(List.of(METHOD_DATA, METHOD_DATA));

        assertThatHtml(parse).containsExactly(
                div(div(content("header")), div(content("source code")), div()),
                div(div(content("header")), div(content("source code")), div())
        );
    }

    @Test
    void parserAppliesCssStylingToTopLevelElement() {
        when(testContentCssHelper.containerCss()).thenReturn(CssElements.css(fontSize(10)));
        List<HtmlValue> parse = parser.parse(List.of(METHOD_DATA));
        assertThat(parse.get(0).asString()).contains("style=");
    }

    private TestMethodData testMethodDataWithSomePopulatedState() {
        TestMethodData testMethodData = testMethodData("someName", new TestMethodSourceCode("sourceCode"));
        testMethodData.updateState(testState);
        return testMethodData;
    }

}