package test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestPathTest {

    @Test
    void getForClassIncludesTestDirectoryInPath() {
        TestPath testClass = TestPath.getForClass("classPath");
        assertThat(testClass.getClassPath()).isEqualTo("src/test/java/classPath.java");
    }

    @Test
    void getForClassConvertsPackageDotsToPathForwardSlashes() {
        TestPath testClass = TestPath.getForClass("package.classPath");
        assertThat(testClass.getClassPath()).isEqualTo("src/test/java/package/classPath.java");
    }

    @Test
    void toPathThrowsExceptionWhenTheFileDoesNotExist() {
        TestPath testClass = TestPath.getForClass("invalidClass");
        Assertions.assertThatThrownBy(testClass::toPath)
                .isInstanceOf(Exception.class) //TODO: change exception type
                .hasMessage("src/test/java/invalidClass.java does not exist.");
    }

    @Test
    void toPathDoesNotThrowExceptionWhenTheFileExists() {
        TestPath testClass = TestPath.getForClass(this.getClass().getName());
        assertThat(testClass).isNotNull();
    }

}