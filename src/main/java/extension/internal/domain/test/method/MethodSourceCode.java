package extension.internal.domain.test.method;

import java.util.Objects;

public final class MethodSourceCode {

    private final String sourceCode;

    public MethodSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String asString() {
        return sourceCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodSourceCode that = (MethodSourceCode) o;
        return Objects.equals(sourceCode, that.sourceCode);
    }
}