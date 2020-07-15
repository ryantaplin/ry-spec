package extension.helpers;

import extension.test.TestMethodData;
import extension.test.TestPath;
import extension.test.TestSourceCode;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static extension.test.TestMethodData.testMethodData;

public class TestMethodExtractor {

    public static List<TestMethodData> getTestMethods(Class<?> subjectClass) {
        return Arrays.stream(subjectClass.getDeclaredMethods())
                .filter(x -> Optional.ofNullable(x.getAnnotation(Test.class)).isPresent())
                .map(method -> getTestMethodData(subjectClass, method))
                .collect(Collectors.toList());
    }

    private static TestMethodData getTestMethodData(Class<?> subjectClass, Method method) {
        return testMethodData(
                method.getName(),
                readSourceCodeFor(subjectClass).extract(method)
        );
    }

    private static TestSourceCode readSourceCodeFor(Class<?> subjectClass) {
        return Optional.ofNullable(subjectClass)
                .map(TestPath::forClass)
                .flatMap(TestSourceCode::read)
                .orElse(TestSourceCode.empty());
    }
}