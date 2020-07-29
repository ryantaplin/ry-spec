package extension.internal.extractor;

import extension.ReportGenerator;

import java.util.Arrays;
import java.util.Optional;

import static java.util.Collections.emptyList;

public class AnnotationExtractor {
    public static ReportGenerator getReportAnnotation(Class<?> classInstance) {
        Class<?> curClass = classInstance;
        while (curClass != null) {
            Optional<ReportGenerator> optionalAnnotation = Optional.of(curClass)
                    .map(x -> Arrays.asList(x.getAnnotations())).orElse(emptyList()).stream()
                    .filter(x -> x.annotationType().equals(ReportGenerator.class))
                    .map(ReportGenerator.class::cast)
                    .findFirst();

            if (optionalAnnotation.isEmpty()) curClass = curClass.getSuperclass();
            else return optionalAnnotation.get();
        }
        throw new IllegalStateException("Expecting @ReportGenerator annotation for test class.");
    }
}
