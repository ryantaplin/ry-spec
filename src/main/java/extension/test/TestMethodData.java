package extension.test;

import extension.test.state.TestState;

import java.util.Objects;
import java.util.Optional;

import static extension.test.TestResult.NOT_RUN;

public class TestMethodData {

    private final String methodName;
    private final TestMethodSourceCode methodSourceCode;
    private TestResult testResult = NOT_RUN;

    private TestState testState;

    private TestMethodData(String methodName, TestMethodSourceCode methodSourceCode) {
        this.methodName = methodName;
        this.methodSourceCode = methodSourceCode;
    }

    public static TestMethodData testMethodData(String methodName, TestMethodSourceCode methodSourceCode) {
        return new TestMethodData(methodName, methodSourceCode);
    }

    public String getName() {
        return methodName;
    }

    public TestResult getResult() {
        return testResult;
    }

    public TestMethodSourceCode getSourceCode() {
        return methodSourceCode;
    }

    public void updateResult(TestResult testResult) {
        this.testResult = testResult;
    }

    public void updateState(TestState state) {
        this.testState = state;
    }

    public Optional<TestState> getOptionalState() {
        return Optional.ofNullable(testState);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestMethodData that = (TestMethodData) o;
        return methodName.equals(that.methodName) &&
                Objects.equals(methodSourceCode, that.methodSourceCode) &&
                testResult == that.testResult;
    }
}
