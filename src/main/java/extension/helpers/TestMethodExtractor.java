package extension.helpers;

import extension.test.TestMethodData;
import extension.test.TestPath;
import extension.test.TestSourceCode;
import extension.test.TestResult;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestMethodExtractor {

    public static List<TestMethodData> getTestMethods(Class<?> subjectClass) {
        TestSourceCode code = getOptionalSourceCode(subjectClass).get(); //.orElseThrow(() -> new Exception("TODO:"));
        return Arrays.stream(subjectClass.getDeclaredMethods())
                .filter(x -> Optional.ofNullable(x.getAnnotation(Test.class)).isPresent())
                .map(method -> new TestMethodData(method.getName(), code.extract(method), TestResult.NOT_RUN))
                .collect(Collectors.toList());
    }

    private static Optional<TestSourceCode> getOptionalSourceCode(Class<?> subjectClass) {
        return Optional.ofNullable(subjectClass)
                .map(TestPath::forClass)
                .flatMap(TestSourceCode::read);
    }
}