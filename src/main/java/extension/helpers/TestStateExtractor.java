package extension.helpers;

import extension.test.state.TestState;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
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
                .filter(TestStateExtractor::isTestStateOrImplementorOfTestState);
    }

    private static boolean isTestStateOrImplementorOfTestState(Field field) {
        return List.of(field.getType().getInterfaces()).contains(TestState.class) ||
                TestState.class.equals(field.getType());
    }

    private static Object extractFieldFromClass(Field field, Object testInstance) {
        try {
            field.setAccessible(true);
            return field.get(testInstance);
        } catch (IllegalAccessException e) {
            return null; //TODO: can't test this because field is final
        }
    }
}
