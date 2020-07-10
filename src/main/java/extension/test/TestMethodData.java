package extension.test;

import java.util.Objects;
import java.util.Optional;

public class TestMethodData {

    private String testMethodName;
    private TestMethodSourceCode testMethodSourceCode;
    private TestResult testResult;
    private TestState testState;

    //private TestMethodInteractions;
    //private TestMethodCaptures;
    //... TODO: testState

    public TestMethodData(String testMethodName, TestMethodSourceCode testMethodSourceCode, TestResult testResult) {
        this.testMethodName = testMethodName;
        this.testMethodSourceCode = testMethodSourceCode;
        this.testResult = testResult;
    }

    public String getName() {
        return testMethodName;
    }

    public TestResult getResult() {
        return testResult;
    }

    public TestMethodSourceCode getSourceCode() {
        return testMethodSourceCode;
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
        return testMethodName.equals(that.testMethodName) &&
                Objects.equals(testMethodSourceCode, that.testMethodSourceCode) &&
                testResult == that.testResult;
    }
}
