package test;

import java.util.Objects;

public class TestMethodSourceCode {

    private String sourceCode;

    public TestMethodSourceCode(String sourceCode) {
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
        TestMethodSourceCode that = (TestMethodSourceCode) o;
        return Objects.equals(sourceCode, that.sourceCode);
    }
}