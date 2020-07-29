package extension.internal.domain.test;

import extension.internal.domain.resources.StubClassWithATestMethod;
import extension.internal.domain.test.InternalPath;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class InternalPathTest {

    @Test
    void pathForReportIncludesHtmlExtensionButDoesNotIncludeTestDirectoryInPath() {
        InternalPath testPath = InternalPath.forClass(StubClassWithATestMethod.class);
        assertThat(testPath.forReport())
                .contains(".html")
                .doesNotContain("src/test/java/");
    }

    @Test
    void pathForSourceCodeIncludesTestDirectoryAndJavaExtensionInPath() throws Exception {
        InternalPath testPath = InternalPath.forClass(StubClassWithATestMethod.class);
        assertThat(testPath.forSourceCode().toString())
                .contains("src/test/java").contains(".java");
    }

    @Test
    void rawPathDoesNotIncludeTestDirectoryOrAnExtensionInPath() {
        InternalPath testPath = InternalPath.forClass(StubClassWithATestMethod.class);
        assertThat(testPath.asRawString())
                .doesNotContain(".html", ".java");
    }

    @Test
    void pathForReportConvertsDotsToForwardSlashes() {
        InternalPath testClass = InternalPath.forClass(StubClassWithATestMethod.class);
        assertThat(testClass.forReport())
                .isEqualTo("extension/internal/domain/resources/StubClassWithATestMethod.html");
    }

    @Test
    void rawStringConvertsDotsToForwardSlashes() {
        InternalPath testClass = InternalPath.forClass(StubClassWithATestMethod.class);
        assertThat(testClass.asRawString())
                .isEqualTo("extension/internal/domain/resources/StubClassWithATestMethod");
    }

    @Test
    void pathForSourceCodeConvertsDotsToForwardSlashes() throws Exception {
        InternalPath testClass = InternalPath.forClass(StubClassWithATestMethod.class);
        assertThat(testClass.forSourceCode().toString())
                .isEqualTo("src/test/java/extension/internal/domain/resources/StubClassWithATestMethod.java");
    }

    @Test
    void toPathThrowsExceptionWhenTheFileDoesExistInTestDirectory() {
        InternalPath testClass = InternalPath.forClass(Date.class);
        Assertions.assertThatThrownBy(testClass::forSourceCode)
                .isInstanceOf(Exception.class) //TODO: change exception type
                .hasMessage("src/test/java/java/util/Date.java does not exist.");
    }

    @Test
    void toPathDoesNotThrowExceptionWhenTheFileExists() {
        InternalPath testClass = InternalPath.forClass(this.getClass());
        assertThat(testClass).isNotNull();
    }

}