package extension.test;

import extension.test.state.DefaultTestState;
import extension.test.state.TestState;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static extension.test.TestMethodData.testMethodData;
import static org.assertj.core.api.Assertions.assertThat;


class TestMethodDataTest {

    private static final TestState TEST_STATE = new DefaultTestState();

    private final TestMethodSourceCode actualTestMethodSourceCode = new TestMethodSourceCode("sourceCode");
    private final TestMethodData actualTestMethodData = testMethodData("name", actualTestMethodSourceCode);

    @Test
    void getNameReturnsCorrectly() {
        assertThat(actualTestMethodData.getName()).isEqualTo("name");
    }

    @Test
    void getSourceCodeReturnsCorrectly() {
        assertThat(actualTestMethodData.getSourceCode()).isEqualTo(actualTestMethodSourceCode);
    }

    @Test
    void getTestResultReturnsCorrectly() {
        assertThat(actualTestMethodData.getResult()).isEqualTo(TestResult.NOT_RUN);
    }

    @Test
    void updateResultChangesTestMethodDataResult() {
        final TestMethodData testMethodData = testMethodData("name", new TestMethodSourceCode("sc"));
        testMethodData.updateResult(TestResult.FAILED);
        assertThat(testMethodData.getResult()).isEqualTo(TestResult.FAILED);
    }

    @Test
    void testStateByDefaultIsEmpty() {
        final TestMethodData testMethodData = testMethodData("name", new TestMethodSourceCode("sc"));
        assertThat(testMethodData.getOptionalState()).isEmpty();
    }

    @Test //TODO: repurpose this to process and return individual things from TestState (i.e List<CapturedInputs>, List<InterestingGivens>)?
    void updateTestStatePopulatesTestState() {
        final TestMethodData testMethodData = testMethodData("name", new TestMethodSourceCode("sc"));
        testMethodData.updateState(TEST_STATE);
        assertThat(testMethodData.getOptionalState().get()).isEqualTo(TEST_STATE);
    }

    @Test
    void testMethodDataIsEqualsWhenAllValuesAreTheSame() {
        TestMethodData expected = testMethodData("name", new TestMethodSourceCode("sourceCode"));
        assertThat(actualTestMethodData).isEqualTo(expected);
    }

    @Test
    void testMethodDataIsNotEqualsWhenTheMethodNameIsDifferent() {
        TestMethodData expected = testMethodData("differentName", new TestMethodSourceCode("sourceCode"));
        assertThat(actualTestMethodData).isNotEqualTo(expected);
    }

    @Test
    void testMethodDataIsNotEqualsWhenTheTestResultIsDifferent() {
        TestMethodData expected = testMethodData("name", new TestMethodSourceCode("sourceCode"));
        expected.updateResult(TestResult.FAILED);
        assertThat(actualTestMethodData).isNotEqualTo(expected);
    }

    @Test
    void testMethodDataIsNotEqualsWhenTheMethodSourceCodeIsDifferent() {
        TestMethodData expected = testMethodData("name", new TestMethodSourceCode("differentSourceCode"));
        assertThat(actualTestMethodData).isNotEqualTo(expected);
    }
}