package extension.test;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestMethodSourceCodeTest {

    @Test
    void asStringReturnsCorrectly() {
        TestMethodSourceCode testClass = new TestMethodSourceCode("something");
        assertThat(testClass.asString()).isEqualTo("something");
    }

    @Test
    void testMethodSourceCodeReturnsStringValue() {
        TestMethodSourceCode testClass = new TestMethodSourceCode("something");
        assertThat(testClass.getSourceCode()).isEqualTo("something");
    }

    @Test
    void testMethodSourceCodeIsEqualsWhenStringValuesAreEqual() {
        TestMethodSourceCode obj1 = new TestMethodSourceCode("value");
        TestMethodSourceCode obj2 = new TestMethodSourceCode("value");
        assertThat(obj1).isEqualTo(obj2);
    }

    @Test
    void testMethodSourceCodeIsNotEqualsWhenStringValuesDiffer() {
        TestMethodSourceCode obj1 = new TestMethodSourceCode("value");
        TestMethodSourceCode obj2 = new TestMethodSourceCode("diffValue");
        assertThat(obj1).isNotEqualTo(obj2);
    }
}