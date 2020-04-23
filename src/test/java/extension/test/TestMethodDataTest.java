package extension.test;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class TestMethodDataTest {

    private final TestMethodSourceCode actualTestMethodSourceCode = new TestMethodSourceCode("sourceCode");
    private final TestMethodData actualTestMethodData = new TestMethodData("name", actualTestMethodSourceCode, TestResult.PASSED);

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
        assertThat(actualTestMethodData.getResult()).isEqualTo(TestResult.PASSED);
    }

    @Test
    void updateResultChangesTestMethodDataResult() {
        final TestMethodData testMethodData = new TestMethodData("name", new TestMethodSourceCode("sc"), TestResult.NOT_RUN);
        testMethodData.updateResult(TestResult.FAILED);
        assertThat(testMethodData.getResult()).isEqualTo(TestResult.FAILED);
    }

    @Test
    void testMethodDataIsEqualsWhenAllValuesAreTheSame() {
        TestMethodData expected = new TestMethodData("name", new TestMethodSourceCode("sourceCode"), TestResult.PASSED);
        assertThat(actualTestMethodData).isEqualTo(expected);
    }

    @Test
    void testMethodDataIsNotEqualsWhenTheTestMethodNameDiffers() {
        TestMethodData expected = new TestMethodData("differentName", new TestMethodSourceCode("sourceCode"), TestResult.PASSED);
        assertThat(actualTestMethodData).isNotEqualTo(expected);
    }

    @Test
    void testMethodDataIsNotEqualsWhenTheTestResultDiffers() {
        TestMethodData expected = new TestMethodData("name", new TestMethodSourceCode("sourceCode"), TestResult.FAILED);
        assertThat(actualTestMethodData).isNotEqualTo(expected);
    }

    @Test
    void testMethodDataIsNotEqualsWhenTheTestMethodSourceCodeDiffers() {
        TestMethodData expected = new TestMethodData("name", new TestMethodSourceCode("differentSourceCode"), TestResult.PASSED);
        assertThat(actualTestMethodData).isNotEqualTo(expected);
    }
}