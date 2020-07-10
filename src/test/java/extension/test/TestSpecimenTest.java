package extension.test;

import extension.test.resources.StubClassWithATestMethod;
import extension.test.resources.StubClassWithTestState;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TestSpecimenTest {


    private final String METHOD_NAME = "testMethod";
    private final TestMethodSourceCode METHOD_SOURCE_CODE = new TestMethodSourceCode("testMethod() {\n\n}\n");

    private final TestMethodData testMethodData = new TestMethodData(METHOD_NAME, METHOD_SOURCE_CODE, TestResult.NOT_RUN);

    //TODO: FIX ME
    @Test
    void getClassPathReturnsClassPathValue() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClassWithATestMethod.class);
        assertThat(testClass.getClassPath()).isEqualTo("src/test/java/extension/test/resources/StubClassWithATestMethod.java");
    }

    @Test
    void getTestMethodDataReturnsAllClassTestMethods() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClassWithATestMethod.class);
        assertThat(testClass.getTestMethodData()).containsExactly(testMethodData);
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
        assertThat(testClass.getTestMethodData()).containsExactlyInAnyOrder(
                new TestMethodData(METHOD_NAME, METHOD_SOURCE_CODE, TestResult.PASSED));
    }

    @Test //TODO: implement better test
    void updateTestMethodMethodStateMutatesCorrectEntryInMap() {
        TestSpecimen testClass = TestSpecimen.initializeForClass(StubClassWithTestState.class);
        testClass.updateTestMethodState("testMethod", TEST_STATE);
        assertThat(testClass.getTestMethodData().get(0).getOptionalState().get()).isEqualTo(TEST_STATE);
    }

    public static final TestState TEST_STATE = new TestState() {
        @Override
        public void putOrAddInteresting(String key, Object value) {}

        @Override
        public Map<String, Object> getInterestings() {
            return null;
        }
    };
}