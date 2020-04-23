package extension.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class TestPath {

    private final String className;

    private TestPath(String className) {
        this.className = className;
    }

    //TODO: Extract testDirectory out - may need to be a property
    public static TestPath getForClass(String className) {
        return new TestPath(className);
    }

    public Path toPath() throws Exception {
        return Optional.of(Paths.get(getClassPath()))
                .filter(p -> Files.exists(p))
                .orElseThrow(() -> new Exception(String.format("%s does not exist.", getClassPath())));
    }

    public String getClassPath() {
        return String.format("src/test/java/%s.java", className.replaceAll("\\.", "/"));
    }
}