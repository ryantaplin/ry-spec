package extension.internal.domain;

import extension.internal.domain.resources.StubClassWithATestMethod;
import extension.internal.domain.resources.StubClassWithDefaultTestState;
import extension.defaults.DefaultTestState;
import extension.TestState;
import extension.internal.domain.test.method.MethodData;
import extension.internal.domain.test.method.MethodSourceCode;
import extension.internal.domain.test.method.Result;
import org.junit.jupiter.api.Test;

import static extension.internal.domain.test.method.MethodData.testMethodData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TestSpecimenTest {


    private final String METHOD_NAME = "testMethod";
    private final MethodSourceCode METHOD_SOURCE_CODE = new MethodSourceCode("testMethod() {\n\n}\n");

    private final MethodData methodData = testMethodData(METHOD_NAME, METHOD_SOURCE_CODE);

    @Test
    void getClassPathReturnsClassPathValue() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClassWithATestMethod.class);
        assertThat(testClass.getTestPath().forReport())
                .isEqualTo("extension/internal/domain/resources/StubClassWithATestMethod.html");
    }

    @Test
    void getTestMethodDataReturnsAllClassTestMethods() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClassWithATestMethod.class);
        assertThat(testClass.getTestMethodDataList()).containsExactly(methodData);
    }

    @Test
    void updateTestMethodDoesNotBlowUpWhenEntryIsNotPresentInHashMap() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClassWithATestMethod.class);
        assertDoesNotThrow(() -> testClass.updateTestMethodResult("invalidTestName", Result.PASSED));
    }

    @Test
    void updateTestMethodResultMutatesCorrectEntryInMap() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClassWithATestMethod.class);
        testClass.updateTestMethodResult("testMethod", Result.PASSED);
        assertThat(testClass.getTestMethodDataList().get(0).getResult()).isEqualTo(Result.PASSED);
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