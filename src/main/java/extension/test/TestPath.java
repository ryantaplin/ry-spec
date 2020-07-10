package extension.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class TestPath {

    private final String value;

    private TestPath(String value) {
        this.value = value;
    }

    //TODO: Extract testDirectory out - may need to be a property
    public static TestPath forClass(Class<?> clazz) {
        return new TestPath(
                getPathAsStringFor(clazz).orElse("")
        );
    }

    public Path asPath() throws Exception {
        return Optional.of(Paths.get(value))
                .filter(p -> Files.exists(p))
                .orElseThrow(() -> new Exception(String.format("%s does not exist.", asString())));
    }

    public String asString() {
        return value;
    }

    private static Optional<String> getPathAsStringFor(Class<?> clazz) {
        //TODO: clazz.getProtectedDomain() for full url?
        return Optional.ofNullable(clazz)
                .flatMap(TestPath::getName)
                .map(name -> name.replaceAll("\\.", "/"))
                .map(name -> String.format("src/test/java/%s.java", name));
    }

    private static Optional<String> getName(Class<?> clazz) {
        return Optional.ofNullable(clazz)
                .map(Class::getName);
    }
}