package extension.internal.domain.test;

import extension.internal.domain.test.method.MethodSourceCode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MethodSourceCodeTest {

    @Test
    void asStringReturnsCorrectly() {
        MethodSourceCode testClass = new MethodSourceCode("something");
        assertThat(testClass.asString()).isEqualTo("something");
    }

    @Test
    void testMethodSourceCodeReturnsStringValue() {
        MethodSourceCode testClass = new MethodSourceCode("something");
        assertThat(testClass.getSourceCode()).isEqualTo("something");
    }

    @Test
    void testMethodSourceCodeIsEqualsWhenStringValuesAreEqual() {
        MethodSourceCode obj1 = new MethodSourceCode("value");
        MethodSourceCode obj2 = new MethodSourceCode("value");
        assertThat(obj1).isEqualTo(obj2);
    }

    @Test
    void testMethodSourceCodeIsNotEqualsWhenStringValuesDiffer() {
        MethodSourceCode obj1 = new MethodSourceCode("value");
        MethodSourceCode obj2 = new MethodSourceCode("diffValue");
        assertThat(obj1).isNotEqualTo(obj2);
    }
}