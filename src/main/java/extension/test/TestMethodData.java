package extension.test;

import java.util.Objects;

public class TestMethodData {

    private String testMethodName;
    private TestMethodSourceCode testMethodSourceCode;
    private TestResult testResult;

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
