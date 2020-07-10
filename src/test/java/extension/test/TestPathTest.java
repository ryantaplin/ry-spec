package extension.test;

import extension.test.resources.StubClassWithATestMethod;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TestPathTest {

    @Test
    void getForClassIncludesTestDirectoryInPath() {
        TestPath testClass = TestPath.forClass(StubClassWithATestMethod.class);
        assertThat(testClass.asString()).contains("src/test/java/");
    }

    @Test
    void getForClassConvertsDotsToForwardSlashes() {
        TestPath testClass = TestPath.forClass(StubClassWithATestMethod.class);
        assertThat(testClass.asString()).isEqualTo("src/test/java/extension/test/resources/StubClassWithATestMethod.java");
    }

    @Test
    void toPathThrowsExceptionWhenTheFileDoesExistInTestDirectory() {
        TestPath testClass = TestPath.forClass(Date.class);
        Assertions.assertThatThrownBy(testClass::asPath)
                .isInstanceOf(Exception.class) //TODO: change exception type
                .hasMessage("src/test/java/java/util/Date.java does not exist.");
    }

    @Test
    void toPathDoesNotThrowExceptionWhenTheFileExists() {
        TestPath testClass = TestPath.forClass(this.getClass());
        assertThat(testClass).isNotNull();
    }

}