package extension.report.parser;

import extension.report.builder.ReportBuilder;
import extension.report.parser.helper.CamelCaseSplitter;
import extension.report.parser.helper.SentenceFormatter;
import extension.report.parser.helper.SourceCodeParser;
import extension.report.parser.html.css.helper.TestContentCssHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import extension.test.TestMethodData;
import extension.test.TestMethodSourceCode;
import extension.test.TestResult;

import java.util.List;

import static extension.report.parser.html.css.CssBuilder.css;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportToHtmlParserTest {

    //TODO: tidy up test; mock out TestSourceCodeToHtmlParser
    private final CamelCaseSplitter ccSplitter = mock(CamelCaseSplitter.class);
    private final SentenceFormatter sFormatter = mock(SentenceFormatter.class);
    private final SourceCodeParser scParser = mock(SourceCodeParser.class);
    private final TestContentCssHelper testContentCssHelper = mock(TestContentCssHelper.class);

    private final TestSourceCodeToHtmlParser scToHtmlParser = new TestSourceCodeToHtmlParser(scParser, ccSplitter, sFormatter, testContentCssHelper);

    private final ReportBuilder reportBuilder = mock(ReportBuilder.class);
    private final ReportToHtmlParser reportToHtmlParser = new ReportToHtmlParser(ccSplitter, scToHtmlParser);

    @BeforeEach
    void setUp() {
        when(reportBuilder.getClassPath()).thenReturn("stubTestClassPath");
        when(reportBuilder.getTestMethodData()).thenReturn(stubTestMethods());

        final Answer<String> withInput = invocation -> {
            Object[] args = invocation.getArguments();
            return (String) args[0];
        };
        when(ccSplitter.split(anyString())).thenAnswer(withInput);
        when(scParser.parse(anyString())).thenAnswer(withInput);
        when(sFormatter.format(anyString())).thenAnswer(withInput);
        when(testContentCssHelper.bodyCss()).thenReturn(null);
        when(testContentCssHelper.headerCss()).thenReturn(null);
        when(testContentCssHelper.containerCss()).thenReturn(null);
    }

    @Test
    void reportParserIncludesReportBuilderTitle() {
        String actual = reportToHtmlParser.parse(reportBuilder);
        assertThat(actual).contains("stubTestClassPath");
    }

    @Test
    void reportParserIncludesReportBuilderMethodNames() {
        String actual = reportToHtmlParser.parse(reportBuilder);
        assertThat(actual).contains("stubMethodName1", "stubMethodName2", "stubMethodName3");
    }

    @Test
    void reportParserIncludesReportBuilderMethodNameSourceCodes() {
        String actual = reportToHtmlParser.parse(reportBuilder);
        assertThat(actual).contains("stubMethodSourceCode1", "stubMethodSourceCode2", "stubMethodSourceCode3");
    }

    @Test
    void reportParserIncludesReportBuilderMethodResults() {
        String actual = reportToHtmlParser.parse(reportBuilder);
        assertThat(actual).contains("NOT_RUN", "PASSED", "FAILED");
    }

    private List<TestMethodData> stubTestMethods() {
        TestMethodData testMethodData1 = new TestMethodData("stubMethodName1", new TestMethodSourceCode("stubMethodSourceCode1"), TestResult.NOT_RUN);
        TestMethodData testMethodData2 = new TestMethodData("stubMethodName2", new TestMethodSourceCode("stubMethodSourceCode2"), TestResult.PASSED);
        TestMethodData testMethodData3 = new TestMethodData("stubMethodName3", new TestMethodSourceCode("stubMethodSourceCode3"), TestResult.FAILED);
        return asList(testMethodData1, testMethodData2,testMethodData3);
    }
}