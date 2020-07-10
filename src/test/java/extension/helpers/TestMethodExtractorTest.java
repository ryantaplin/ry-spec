package extension.helpers;

import extension.test.TestMethodData;
import extension.test.TestMethodSourceCode;
import extension.test.TestResult;
import extension.test.resources.EmptyStubClass;
import extension.test.resources.StubClassWithATestMethod;
import extension.test.resources.StubClassWithMultipleTestMethods;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestMethodExtractorTest {

    public static final String METHOD_NAME = "testMethod";
    public static final String ANOTHER_METHOD_NAME = "anotherTestMethod";
    public static final TestMethodSourceCode METHOD_SOURCE_CODE = new TestMethodSourceCode("testMethod() {\n\n}\n");
    public static final TestMethodSourceCode ANOTHER_METHOD_SOURCE_CODE = new TestMethodSourceCode("anotherTestMethod() {\n}\n");

    @Test
    void returnsTestMethodWhenClassContainsATestMethod() {
        List<TestMethodData> testMethods = TestMethodExtractor.getTestMethods(StubClassWithATestMethod.class);
        assertThat(testMethods).contains(new TestMethodData(METHOD_NAME, METHOD_SOURCE_CODE, TestResult.NOT_RUN));
    }

    @Test
    void returnsTestMethodsWhenClassContainsMultipleTestMethods() {
        List<TestMethodData> testMethods = TestMethodExtractor.getTestMethods(StubClassWithMultipleTestMethods.class);
        assertThat(testMethods).containsExactlyInAnyOrder(
                new TestMethodData(METHOD_NAME, METHOD_SOURCE_CODE, TestResult.NOT_RUN),
                new TestMethodData(ANOTHER_METHOD_NAME, ANOTHER_METHOD_SOURCE_CODE, TestResult.NOT_RUN));
    }

    @Test
    void returnsNoTestMethodsWhenClassContainsNoTestMethods() {
        List<TestMethodData> testMethods = TestMethodExtractor.getTestMethods(EmptyStubClass.class);
        assertThat(testMethods).isEmpty();
    }
}