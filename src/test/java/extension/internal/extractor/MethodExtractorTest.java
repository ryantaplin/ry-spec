package extension.internal.extractor;

import extension.internal.domain.test.method.MethodData;
import extension.internal.domain.test.method.MethodSourceCode;
import extension.internal.domain.resources.EmptyStubClass;
import extension.internal.domain.resources.StubClassWithATestMethod;
import extension.internal.domain.resources.StubClassWithMultipleTestMethods;
import org.junit.jupiter.api.Test;

import java.util.List;

import static extension.internal.domain.test.method.MethodData.testMethodData;
import static org.assertj.core.api.Assertions.assertThat;

class MethodExtractorTest {

    public static final String METHOD_NAME = "testMethod";
    public static final String ANOTHER_METHOD_NAME = "anotherTestMethod";
    public static final MethodSourceCode METHOD_SOURCE_CODE = new MethodSourceCode("testMethod() {\n\n}\n");
    public static final MethodSourceCode ANOTHER_METHOD_SOURCE_CODE = new MethodSourceCode("anotherTestMethod() {\n}\n");

    @Test
    void returnsTestMethodWhenClassContainsATestMethod() {
        List<MethodData> testMethods = MethodExtractor.getTestMethods(StubClassWithATestMethod.class);
        assertThat(testMethods).contains(testMethodData(METHOD_NAME, METHOD_SOURCE_CODE));
    }

    @Test
    void returnsTestMethodsWhenClassContainsMultipleTestMethods() {
        List<MethodData> testMethods = MethodExtractor.getTestMethods(StubClassWithMultipleTestMethods.class);
        assertThat(testMethods).containsExactlyInAnyOrder(
                testMethodData(METHOD_NAME, METHOD_SOURCE_CODE),
                testMethodData(ANOTHER_METHOD_NAME, ANOTHER_METHOD_SOURCE_CODE)
        );
    }

    @Test
    void returnsNoTestMethodsWhenClassContainsNoTestMethods() {
        List<MethodData> testMethods = MethodExtractor.getTestMethods(EmptyStubClass.class);
        assertThat(testMethods).isEmpty();
    }
}