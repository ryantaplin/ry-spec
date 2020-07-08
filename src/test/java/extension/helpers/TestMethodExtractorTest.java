package extension.helpers;

import extension.test.TestMethodData;
import extension.test.TestMethodSourceCode;
import extension.test.resources.EmptyStubClass;
import extension.test.resources.StubClass;
import extension.test.TestResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestMethodExtractorTest {

    public static final TestMethodSourceCode METHOD_SOURCE_CODE = new TestMethodSourceCode("stubExampleOne() {\n\n}\n");
    public static final String METHOD_NAME = "stubExampleOne";

    @Test
    void returnsTestMethodsWhenClassContainsTestMethods() {
        List<TestMethodData> testMethods = TestMethodExtractor.getTestMethods(StubClass.class);
        assertThat(testMethods).contains(new TestMethodData(METHOD_NAME, METHOD_SOURCE_CODE, TestResult.NOT_RUN));
    }

    @Test
    void returnsNoTestMethodsWhenClassContainsNoTestMethods() {
        List<TestMethodData> testMethods = TestMethodExtractor.getTestMethods(EmptyStubClass.class);
        assertThat(testMethods).isEmpty();
    }
}