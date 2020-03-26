package extension.report.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.TestMethodData;
import test.TestMethodSourceCode;
import test.TestResult;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ReportBuilderTest {

    private final String TEST_CLASS = "testClass";

    private final String TEST_METHOD = "testMethod";
    private final TestMethodData testMethodData1 = new TestMethodData(String.format("%s1", TEST_METHOD), new TestMethodSourceCode(""), TestResult.NOT_RUN);
    private final TestMethodData testMethodData2 = new TestMethodData(String.format("%s2", TEST_METHOD), new TestMethodSourceCode(""), TestResult.NOT_RUN);

    @Test
    void getClassPathReturnsInitializedValue() {
        ReportBuilder testClass = ReportBuilder.init(TEST_CLASS, emptyList());
        assertThat(testClass.getClassPath()).isEqualTo(TEST_CLASS);
    }

    @Test
    void getTestMethodDataReturnsInitializedList() {
        ReportBuilder testClass = ReportBuilder.init(TEST_CLASS, Arrays.asList(testMethodData1, testMethodData2));
        assertThat(testClass.getTestMethodData()).containsExactlyInAnyOrder(testMethodData1, testMethodData2);
    }

    @Test
    void updateTestMethodDoesNotBlowUpWhenEntryIsNotPresentInHashMap() {
        ReportBuilder testClass = ReportBuilder.init(TEST_CLASS, emptyList());
        assertDoesNotThrow(() -> testClass.updateTestMethodResult("invalidTestName", TestResult.PASSED));
    }

    @Test
    void updateTestMethodMutatesCorrectEntryInHashMap() {
        ReportBuilder testClass = ReportBuilder.init(TEST_CLASS, Arrays.asList(testMethodData1, testMethodData2));
        testClass.updateTestMethodResult("testMethod1", TestResult.PASSED);
        assertThat(testClass.getTestMethodData()).containsExactlyInAnyOrder(new TestMethodData(String.format("%s1", TEST_METHOD), new TestMethodSourceCode(""), TestResult.PASSED), testMethodData2);
    }
}