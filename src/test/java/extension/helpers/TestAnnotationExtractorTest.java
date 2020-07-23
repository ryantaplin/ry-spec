package extension.helpers;

import extension.ReportGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TestAnnotationExtractorTest {

    @Test
    void returnsAnnotationFromTestClass() {
        ReportGenerator result = TestAnnotationExtractor.getReportAnnotation(TestClassWithAnnotation.class);
        assertThat(result).isNotNull();
    }

    @Test
    void returnsAnnotationFromTestClassSuperClasses() {
        ReportGenerator result = TestAnnotationExtractor.getReportAnnotation(TestClassWithNestedAnnotation.class);
        assertThat(result).isNotNull();
    }

    @Test
    void throwsExceptionWhenNoAnnotationIsFound() {
        assertThatThrownBy(() -> TestAnnotationExtractor.getReportAnnotation(TestClassWithoutAnnotation.class))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Expecting @ReportGenerator annotation for test class.");
    }

    @ReportGenerator
    class TestClassWithAnnotation { }

    class TestClassWithoutAnnotation { }

    class TestClassWithNestedAnnotation extends AnotherClass { }
    class AnotherClass extends TestClassWithAnnotation { }
}