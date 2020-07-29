package extension.internal.domain.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class InternalPath {

    private final String value;

    private InternalPath(String value) {
        this.value = value;
    }

    //TODO: Extract testDirectory out - may need to be a property
    public static InternalPath forClass(Class<?> clazz) {
        return new InternalPath(getPathAsStringFor(clazz).orElse(""));
    }

    public Path forSourceCode() throws Exception {
        String sourcePath = String.format("src/test/java/%s.java", value);
        return Optional.of(Paths.get(sourcePath))
                .filter(p -> Files.exists(p))
                .orElseThrow(() -> new Exception(String.format("%s does not exist.", sourcePath)));
    }

    public String forReport() {
        return String.format("%s.html", value);
    }

    public String asRawString() {
        return value;
    }

    private static Optional<String> getPathAsStringFor(Class<?> clazz) {
        return Optional.ofNullable(clazz)
                .flatMap(InternalPath::getName)
                .map(name -> name.replaceAll("\\.", "/"));
    }

    private static Optional<String> getName(Class<?> clazz) {
        return Optional.ofNullable(clazz)
                .map(Class::getName);
    }
}