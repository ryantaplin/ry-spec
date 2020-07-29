package extension.internal.domain.test;

import extension.internal.domain.resources.EmptyStubClass;
import extension.internal.domain.resources.StubClassWithATestMethod;
import extension.internal.domain.test.InternalPath;
import extension.internal.domain.test.SourceCode;
import extension.internal.domain.test.method.MethodSourceCode;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static extension.internal.domain.test.InternalPath.forClass;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SourceCodeTest {

    @Test
    void readSuccessfullyReturnsTestSourceCode() {
        final InternalPath emptyStubClass = InternalPath.forClass(EmptyStubClass.class);
        SourceCode actualSourceCode = SourceCode.read(emptyStubClass).orElseThrow();
        assertThat(actualSourceCode.asString()).isEqualTo(EXPECTED_EMPTY_STUB_CLASS_SOURCE_CODE);
    }

    @Test
    void readReturnsEmptyWhenPathReturnsEmpty() throws Exception {
        final InternalPath emptyTestPath = mock(InternalPath.class);
        when(emptyTestPath.forSourceCode()).thenThrow(new Exception("Boom!"));
        assertThat(SourceCode.read(emptyTestPath)).isEmpty();  //TODO: Logging test
    }

    @Test
    void readReturnsEmptyWhenFileCouldNotBeFoundInTestDirectory() {
        final InternalPath invalidTestPath = InternalPath.forClass(Date.class);
        assertThat(SourceCode.read(invalidTestPath)).isEmpty(); //TODO: Logging test
    }

    @Test
    void readReturnsEmptyWhenNullIsProvided() {
        final InternalPath invalidTestPath = InternalPath.forClass(null);
        assertThat(SourceCode.read(invalidTestPath)).isEmpty(); //TODO: Logging test
    }

    //TODO: this will change - NoContents, 1LineContents, MultiLineContents
    @Test
    void successfullyExtractMethodContents() throws Exception {
        final InternalPath testPath = forClass(StubClassWithATestMethod.class);
        final SourceCode sourceCode = SourceCode.read(testPath).orElseThrow();

        MethodSourceCode actualMethodCode = sourceCode.extract(StubClassWithATestMethod.class.getDeclaredMethod("testMethod"));
        assertThat(actualMethodCode.asString()).isEqualTo(EXPECTED_METHOD_SOURCE_CODE);
    }

    public static final String EXPECTED_METHOD_SOURCE_CODE = "testMethod() {\n" +
            "\n" +
            "}\n";

    public static final String EXPECTED_EMPTY_STUB_CLASS_SOURCE_CODE = "package extension.internal.domain.resources;\n" +
            "\n" +
            "public final class EmptyStubClass {\n" +
            "}";
}