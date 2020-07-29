package extension.internal.domain.test.method;

import extension.TestState;

import java.util.Objects;
import java.util.Optional;

public final class MethodData {

    private final String methodName;
    private final MethodSourceCode methodSourceCode;

    private Result result;
    private TestState testState;

    private MethodData(String methodName, MethodSourceCode methodSourceCode) {
        this.methodName = methodName;
        this.methodSourceCode = methodSourceCode;
        this.result = Result.NOT_RUN;
    }

    public static MethodData testMethodData(String methodName, MethodSourceCode methodSourceCode) {
        return new MethodData(methodName, methodSourceCode);
    }

    public String getName() {
        return methodName;
    }

    public Result getResult() {
        return result;
    }

    public MethodSourceCode getSourceCode() {
        return methodSourceCode;
    }

    public void updateResult(Result result) {
        this.result = result;
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
        MethodData that = (MethodData) o;
        return methodName.equals(that.methodName) &&
                Objects.equals(methodSourceCode, that.methodSourceCode) &&
                result == that.result;
    }
}
