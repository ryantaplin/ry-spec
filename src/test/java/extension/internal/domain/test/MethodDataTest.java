package extension.internal.domain.test;

import extension.defaults.DefaultTestState;
import extension.TestState;
import extension.internal.domain.test.method.MethodData;
import extension.internal.domain.test.method.MethodSourceCode;
import extension.internal.domain.test.method.Result;
import org.junit.jupiter.api.Test;

import static extension.internal.domain.test.method.MethodData.testMethodData;
import static org.assertj.core.api.Assertions.assertThat;


class MethodDataTest {

    private static final TestState TEST_STATE = new DefaultTestState();

    private final MethodSourceCode actualMethodSourceCode = new MethodSourceCode("sourceCode");
    private final MethodData actualMethodData = testMethodData("name", actualMethodSourceCode);

    @Test
    void getNameReturnsCorrectly() {
        assertThat(actualMethodData.getName()).isEqualTo("name");
    }

    @Test
    void getSourceCodeReturnsCorrectly() {
        assertThat(actualMethodData.getSourceCode()).isEqualTo(actualMethodSourceCode);
    }

    @Test
    void getTestResultReturnsCorrectly() {
        assertThat(actualMethodData.getResult()).isEqualTo(Result.NOT_RUN);
    }

    @Test
    void updateResultChangesTestMethodDataResult() {
        final MethodData methodData = testMethodData("name", new MethodSourceCode("sc"));
        methodData.updateResult(Result.FAILED);
        assertThat(methodData.getResult()).isEqualTo(Result.FAILED);
    }

    @Test
    void testStateByDefaultIsEmpty() {
        final MethodData methodData = testMethodData("name", new MethodSourceCode("sc"));
        assertThat(methodData.getOptionalState()).isEmpty();
    }

    @Test //TODO: repurpose this to process and return individual things from TestState (i.e List<CapturedInputs>, List<InterestingGivens>)?
    void updateTestStatePopulatesTestState() {
        final MethodData methodData = testMethodData("name", new MethodSourceCode("sc"));
        methodData.updateState(TEST_STATE);
        assertThat(methodData.getOptionalState().get()).isEqualTo(TEST_STATE);
    }

    @Test
    void testMethodDataIsEqualsWhenAllValuesAreTheSame() {
        MethodData expected = testMethodData("name", new MethodSourceCode("sourceCode"));
        assertThat(actualMethodData).isEqualTo(expected);
    }

    @Test
    void testMethodDataIsNotEqualsWhenTheMethodNameIsDifferent() {
        MethodData expected = testMethodData("differentName", new MethodSourceCode("sourceCode"));
        assertThat(actualMethodData).isNotEqualTo(expected);
    }

    @Test
    void testMethodDataIsNotEqualsWhenTheTestResultIsDifferent() {
        MethodData expected = testMethodData("name", new MethodSourceCode("sourceCode"));
        expected.updateResult(Result.FAILED);
        assertThat(actualMethodData).isNotEqualTo(expected);
    }

    @Test
    void testMethodDataIsNotEqualsWhenTheMethodSourceCodeIsDifferent() {
        MethodData expected = testMethodData("name", new MethodSourceCode("differentSourceCode"));
        assertThat(actualMethodData).isNotEqualTo(expected);
    }
}