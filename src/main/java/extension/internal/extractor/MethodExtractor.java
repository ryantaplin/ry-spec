package extension.internal.extractor;

import extension.internal.domain.test.method.MethodData;
import extension.internal.domain.test.InternalPath;
import extension.internal.domain.test.SourceCode;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static extension.internal.domain.test.method.MethodData.testMethodData;

public class MethodExtractor {

    public static List<MethodData> getTestMethods(Class<?> subjectClass) {
        return Arrays.stream(subjectClass.getDeclaredMethods())
                .filter(x -> Optional.ofNullable(x.getAnnotation(Test.class)).isPresent())
                .map(method -> getTestMethodData(subjectClass, method))
                .collect(Collectors.toList());
    }

    private static MethodData getTestMethodData(Class<?> subjectClass, Method method) {
        return testMethodData(
                method.getName(),
                readSourceCodeFor(subjectClass).extract(method)
        );
    }

    private static SourceCode readSourceCodeFor(Class<?> subjectClass) {
        return Optional.ofNullable(subjectClass)
                .map(InternalPath::forClass)
                .flatMap(SourceCode::read)
                .orElse(SourceCode.empty());
    }
}