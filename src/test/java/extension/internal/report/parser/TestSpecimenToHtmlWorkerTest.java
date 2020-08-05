package extension.internal.report.parser;

import extension.internal.report.parser.html.HtmlValue;
import extension.internal.report.parser.html.parser.StringToHtmlHeaderParser;
import extension.internal.report.parser.html.parser.SourceCodeToHtmlParser;
import extension.internal.report.parser.html.parser.StateToHtmlParser;
import extension.internal.domain.test.method.MethodData;
import extension.internal.domain.test.method.MethodSourceCode;
import extension.internal.domain.test.method.Result;
import extension.TestState;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.element.DivElement.div;
import static extension.internal.domain.test.method.MethodData.testMethodData;
import static helpers.assertions.HtmlListAssertions.assertThatHtml;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TestSpecimenToHtmlWorkerTest {

    private final StateToHtmlParser stateToHtmlParser = mock(StateToHtmlParser.class);
    private final StringToHtmlHeaderParser stringToHtmlHeaderParser = mock(StringToHtmlHeaderParser.class);
    private final SourceCodeToHtmlParser sourceCodeToHtmlParser = mock(SourceCodeToHtmlParser.class);


    private final TestSpecimenToHtmlWorker parser = new TestSpecimenToHtmlWorker(
            stringToHtmlHeaderParser,
            sourceCodeToHtmlParser,
            stateToHtmlParser
    );

    private TestState testState = mock(TestState.class);
    private MethodData METHOD_DATA = testMethodData("someName", new MethodSourceCode("sourceCode"));

    @Test
    void returnsEmptyListWhenInputIsEmpty() {
        List<HtmlValue> result = parser.parse(Collections.emptyList());
        assertThatHtml(result).isEmpty();
    }

    @Test
    void headerParserIsInvokedWithExpectedContent() {
        parser.parse(List.of(METHOD_DATA));
        verify(stringToHtmlHeaderParser).parse("someName", Result.NOT_RUN);
    }

    @Test
    void sourceCodeParserIsInvokedWithExpectedContent() {
        parser.parse(List.of(METHOD_DATA));
        verify(sourceCodeToHtmlParser).parse(new MethodSourceCode("sourceCode"));
    }

    @Test
    void stateParserIsInvokedWithNullWhenNotPresent() {
        parser.parse(List.of(METHOD_DATA));
        verify(stateToHtmlParser).parse(null);
    }

    @Test
    void stateParserIsInvokedWithExpectedContent() {
        parser.parse(List.of(testMethodDataWithSomePopulatedState()));
        verify(stateToHtmlParser).parse(testState);
    }

    @Test
    void parsersAreInvokedForNAmountOfMethodDataProvided() {
        parser.parse(List.of(METHOD_DATA, METHOD_DATA));
        verify(stringToHtmlHeaderParser, times(2)).parse(anyString(), any());
        verify(sourceCodeToHtmlParser, times(2)).parse(any());
        verify(stateToHtmlParser, times(2)).parse(any());
    }

    @Test
    void parserReturnsExpectedHtmlElementsForInRelationToMethodsProvided() {
        when(stringToHtmlHeaderParser.parse(any(), any())).thenReturn(div(content("header")));
        when(sourceCodeToHtmlParser.parse(any())).thenReturn(div(content("source code")));
        when(stateToHtmlParser.parse(any())).thenReturn(div());

        List<HtmlValue> parse = parser.parse(List.of(METHOD_DATA, METHOD_DATA));

        assertThatHtml(parse).containsExactly(
                div(div(content("header")), div(content("source code")), div()).withClassName("container"),
                div(div(content("header")), div(content("source code")), div()).withClassName("container")
        );
    }

    private MethodData testMethodDataWithSomePopulatedState() {
        MethodData methodData = testMethodData("someName", new MethodSourceCode("sourceCode"));
        methodData.updateState(testState);
        return methodData;
    }

}