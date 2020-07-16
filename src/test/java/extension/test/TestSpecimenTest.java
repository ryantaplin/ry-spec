package extension.test;

import extension.test.resources.StubClassWithATestMethod;
import extension.test.resources.StubClassWithDefaultTestState;
import extension.test.state.DefaultTestState;
import extension.test.state.TestState;
import org.junit.jupiter.api.Test;

import static extension.test.TestMethodData.testMethodData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TestSpecimenTest {


    private final String METHOD_NAME = "testMethod";
    private final TestMethodSourceCode METHOD_SOURCE_CODE = new TestMethodSourceCode("testMethod() {\n\n}\n");

    private final TestMethodData testMethodData = testMethodData(METHOD_NAME, METHOD_SOURCE_CODE);

    @Test
    void getClassPathReturnsClassPathValue() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClassWithATestMethod.class);
        assertThat(testClass.getTestPath().forReport())
                .isEqualTo("extension/test/resources/StubClassWithATestMethod.html");
    }

    @Test
    void getTestMethodDataReturnsAllClassTestMethods() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClassWithATestMethod.class);
        assertThat(testClass.getTestMethodDataList()).containsExactly(testMethodData);
    }

    @Test
    void updateTestMethodDoesNotBlowUpWhenEntryIsNotPresentInHashMap() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClassWithATestMethod.class);
        assertDoesNotThrow(() -> testClass.updateTestMethodResult("invalidTestName", TestResult.PASSED));
    }

    @Test
    void updateTestMethodResultMutatesCorrectEntryInMap() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClassWithATestMethod.class);
        testClass.updateTestMethodResult("testMethod", TestResult.PASSED);
        assertThat(testClass.getTestMethodDataList().get(0).getResult()).isEqualTo(TestResult.PASSED);
    }

    @Test
    void updateTestMethodMethodStateMutatesCorrectEntryInMap() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClassWithDefaultTestState.class);
        testClass.updateTestMethodState("testMethod", TEST_STATE);
        assertThat(testClass.getTestMethodDataList().get(0).getOptionalState())
                .isPresent().get()
                .isEqualTo(TEST_STATE);
    }

    public static final TestState TEST_STATE = new DefaultTestState();
}