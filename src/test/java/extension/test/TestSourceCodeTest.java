package extension.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import extension.test.resources.StubExtractClass;

import static org.assertj.core.api.Assertions.assertThat;
import static extension.test.TestPath.getForClass;


class TestSourceCodeTest {

    //TODO: separate - class source code reader && method source code extractor

    @Test
    void readSuccessfullyReturnsTestSourceCode() throws Exception {
        final TestPath emptyStubClass = getForClass("extension.test.resources.EmptyStubExtractClass");

        TestSourceCode actualSourceCode = TestSourceCode.read(emptyStubClass);
        assertThat(actualSourceCode.asString()).isEqualTo(EXPECTED_EMPTY_STUB_CLASS_SOURCE_CODE);
    }

    @Test
    void readThrowsExceptionWhenFileCouldNotBeFound() {
        final TestPath invalidTestPath = getForClass("invalidTestClass");

        Assertions.assertThatThrownBy(() -> TestSourceCode.read(invalidTestPath))
                .isInstanceOf(Exception.class); //TODO: throw better exception

    }

    //TODO: this will change - NoContents, 1LineContents, MultiLineContents
    @Test
    void extractSuccessfullyExtractMethodContents() throws Exception {
        final TestPath testPath = getForClass("extension.test.resources.StubExtractClass");
        final TestSourceCode sourceCode = TestSourceCode.read(testPath);

        TestMethodSourceCode actualMethodCode = sourceCode.extract(StubExtractClass.class.getDeclaredMethod("stubExampleOne"));
        assertThat(actualMethodCode.asString()).isEqualTo(EXPECTED_METHOD_SOURCE_CODE);
    }

    public static final String EXPECTED_METHOD_SOURCE_CODE = "stubExampleOne() {\n" +
            "\n" +
            "}\n";

    public static final String EXPECTED_EMPTY_STUB_CLASS_SOURCE_CODE = "package extension.test.resources;\n" +
            "\n" +
            "public class EmptyStubExtractClass {\n" +
            "}";
}