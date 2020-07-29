package extension.internal.extractor;

import extension.ReportGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnnotationExtractorTest {

    @Test
    void returnsAnnotationFromTestClass() {
        ReportGenerator result = AnnotationExtractor.getReportAnnotation(TestClassWithAnnotation.class);
        assertThat(result).isNotNull();
    }

    @Test
    void returnsAnnotationFromTestClassSuperClasses() {
        ReportGenerator result = AnnotationExtractor.getReportAnnotation(TestClassWithNestedAnnotation.class);
        assertThat(result).isNotNull();
    }

    @Test
    void throwsExceptionWhenNoAnnotationIsFound() {
        assertThatThrownBy(() -> AnnotationExtractor.getReportAnnotation(TestClassWithoutAnnotation.class))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Expecting @ReportGenerator annotation for test class.");
    }

    //TODO: misc annotations @LinkingNote(...), @Note(text..), @TrackingIssue(Jira..)

    @ReportGenerator
    class TestClassWithAnnotation { }

    class TestClassWithoutAnnotation { }

    class TestClassWithNestedAnnotation extends AnotherClass { }
    class AnotherClass extends TestClassWithAnnotation { }
}