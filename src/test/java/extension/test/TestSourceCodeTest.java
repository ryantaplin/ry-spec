package extension.test;

import extension.test.resources.EmptyStubClass;
import extension.test.resources.StubClassWithATestMethod;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static extension.test.TestPath.forClass;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TestSourceCodeTest {

    @Test
    void readSuccessfullyReturnsTestSourceCode() {
        final TestPath emptyStubClass = TestPath.forClass(EmptyStubClass.class);

        TestSourceCode actualSourceCode = TestSourceCode.read(emptyStubClass).orElseThrow();
        assertThat(actualSourceCode.asString()).isEqualTo(EXPECTED_EMPTY_STUB_CLASS_SOURCE_CODE);
    }

    @Test
    void readReturnsEmptyWhenPathReturnsEmpty() throws Exception {
        final TestPath emptyTestPath = mock(TestPath.class);
        when(emptyTestPath.asPath()).thenThrow(new Exception("Boom!"));
        assertThat(TestSourceCode.read(emptyTestPath)).isEmpty();  //TODO: Logging test
    }

    @Test
    void readReturnsEmptyWhenFileCouldNotBeFoundInTestDirectory() {
        final TestPath invalidTestPath = TestPath.forClass(Date.class);
        assertThat(TestSourceCode.read(invalidTestPath)).isEmpty(); //TODO: Logging test
    }

    @Test
    void readReturnsEmptyWhenNullIsProvided() {
        final TestPath invalidTestPath = TestPath.forClass(null);
        assertThat(TestSourceCode.read(invalidTestPath)).isEmpty(); //TODO: Logging test
    }

    //TODO: this will change - NoContents, 1LineContents, MultiLineContents
    @Test
    void extractSuccessfullyExtractMethodContents() throws Exception {
        final TestPath testPath = forClass(StubClassWithATestMethod.class);
        final TestSourceCode sourceCode = TestSourceCode.read(testPath).orElseThrow();

        TestMethodSourceCode actualMethodCode = sourceCode.extract(StubClassWithATestMethod.class.getDeclaredMethod("testMethod"));
        assertThat(actualMethodCode.asString()).isEqualTo(EXPECTED_METHOD_SOURCE_CODE);
    }

    public static final String EXPECTED_METHOD_SOURCE_CODE = "testMethod() {\n" +
            "\n" +
            "}\n";

    public static final String EXPECTED_EMPTY_STUB_CLASS_SOURCE_CODE = "package extension.test.resources;\n" +
            "\n" +
            "public final class EmptyStubClass {\n" +
            "}";
}