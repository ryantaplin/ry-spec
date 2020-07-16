package extension.test;

import extension.test.resources.StubClassWithATestMethod;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TestPathTest {

    @Test
    void pathForReportIncludesHtmlExtensionButDoesNotIncludeTestDirectoryInPath() {
        TestPath testPath = TestPath.forClass(StubClassWithATestMethod.class);
        assertThat(testPath.forReport())
                .contains(".html")
                .doesNotContain("src/test/java/");
    }

    @Test
    void pathForSourceCodeIncludesTestDirectoryAndJavaExtensionInPath() throws Exception {
        TestPath testPath = TestPath.forClass(StubClassWithATestMethod.class);
        assertThat(testPath.forSourceCode().toString())
                .contains("src/test/java").contains(".java");
    }

    @Test
    void rawPathDoesNotIncludeTestDirectoryOrAnExtensionInPath() {
        TestPath testPath = TestPath.forClass(StubClassWithATestMethod.class);
        assertThat(testPath.asRawString())
                .doesNotContain(".html", ".java");
    }

    @Test
    void pathForReportConvertsDotsToForwardSlashes() {
        TestPath testClass = TestPath.forClass(StubClassWithATestMethod.class);
        assertThat(testClass.forReport())
                .isEqualTo("extension/test/resources/StubClassWithATestMethod.html");
    }

    @Test
    void rawStringConvertsDotsToForwardSlashes() {
        TestPath testClass = TestPath.forClass(StubClassWithATestMethod.class);
        assertThat(testClass.asRawString())
                .isEqualTo("extension/test/resources/StubClassWithATestMethod");
    }

    @Test
    void pathForSourceCodeConvertsDotsToForwardSlashes() throws Exception {
        TestPath testClass = TestPath.forClass(StubClassWithATestMethod.class);
        assertThat(testClass.forSourceCode().toString())
                .isEqualTo("src/test/java/extension/test/resources/StubClassWithATestMethod.java");
    }

    @Test
    void toPathThrowsExceptionWhenTheFileDoesExistInTestDirectory() {
        TestPath testClass = TestPath.forClass(Date.class);
        Assertions.assertThatThrownBy(testClass::forSourceCode)
                .isInstanceOf(Exception.class) //TODO: change exception type
                .hasMessage("src/test/java/java/util/Date.java does not exist.");
    }

    @Test
    void toPathDoesNotThrowExceptionWhenTheFileExists() {
        TestPath testClass = TestPath.forClass(this.getClass());
        assertThat(testClass).isNotNull();
    }

}