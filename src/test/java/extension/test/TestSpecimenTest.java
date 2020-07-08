package extension.test;

import extension.test.TestMethodData;
import extension.test.TestMethodSourceCode;
import extension.test.TestSpecimen;
import extension.test.resources.StubClass;
import extension.test.TestResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TestSpecimenTest {


    private final String METHOD_NAME = "stubExampleOne";
    private final TestMethodSourceCode METHOD_SOURCE_CODE = new TestMethodSourceCode("stubExampleOne() {\n\n}\n");

    private final TestMethodData testMethodData = new TestMethodData(METHOD_NAME, METHOD_SOURCE_CODE, TestResult.NOT_RUN);

    //TODO: FIX ME

    @Test
    void getClassPathReturnsClassPathValue() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClass.class);
        assertThat(testClass.getClassPath()).isEqualTo("src/test/java/extension/test/resources/StubClass.java");
    }

    @Test
    void getTestMethodDataReturnsAllClassTestMethods() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClass.class);
        assertThat(testClass.getTestMethodData()).containsExactly(testMethodData);
    }

    @Test
    void updateTestMethodDoesNotBlowUpWhenEntryIsNotPresentInHashMap() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClass.class);
        assertDoesNotThrow(() -> testClass.updateTestMethodResult("invalidTestName", TestResult.PASSED));
    }

    @Test
    void updateTestMethodMutatesCorrectEntryInHashMap() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClass.class);
        testClass.updateTestMethodResult("stubExampleOne", TestResult.PASSED);
        assertThat(testClass.getTestMethodData()).containsExactlyInAnyOrder(
                new TestMethodData(METHOD_NAME, METHOD_SOURCE_CODE, TestResult.PASSED));
    }
}