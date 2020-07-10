package extension.helpers;

import extension.test.TestState;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class TestStateExtractor {
    public static Optional<TestState> getOptionalTestStateFrom(Object classInstance) {
        Class<?> curClass = Optional.ofNullable(classInstance).map(Object::getClass).orElse(null);
        while (curClass != null) {
            Optional<TestState> optionalField = findDeclaredTestContainerFor(curClass)
                    .map(field -> extractFieldFromClass(field, classInstance))
                    .map(TestState.class::cast)
                    .filter(Objects::nonNull)
                    .findFirst();

            if (optionalField.isEmpty()) curClass = curClass.getSuperclass();
            else return optionalField;
        }
        return Optional.empty();
    }

    private static Stream<Field> findDeclaredTestContainerFor(Class<?> curClass) {
        return Arrays.stream(curClass.getDeclaredFields())
                .filter(field -> TestState.class.equals(field.getType()));
    }

    private static Object extractFieldFromClass(Field field, Object testInstance) {
        try {
            field.setAccessible(true);
            return field.get(testInstance);
        } catch (IllegalAccessException e) {
            //TODO: can't test this without extracting and mocking field? likelihood hood never happens?
            return null;
        }
    }
}
