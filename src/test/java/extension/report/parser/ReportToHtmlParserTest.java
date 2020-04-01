package extension.report.parser;

import extension.report.builder.ReportBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.TestMethodData;
import test.TestMethodSourceCode;
import test.TestResult;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportToHtmlParserTest {

    private final ReportBuilder reportBuilder = mock(ReportBuilder.class);
    private final ReportToHtmlParser reportToHtmlParser = new ReportToHtmlParser();

    @BeforeEach
    void setUp() {
        when(reportBuilder.getClassPath()).thenReturn("stubTestClassPath");
        when(reportBuilder.getTestMethodData()).thenReturn(stubTestMethods());
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