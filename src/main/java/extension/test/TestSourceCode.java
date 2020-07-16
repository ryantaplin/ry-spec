package extension.test;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.Optional;

public class TestSourceCode {

    private String sourceCode;

    TestSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public static Optional<TestSourceCode> read(TestPath testPath) {
        try {
            return Optional.of(new TestSourceCode(String.join("\n", Files.readAllLines(testPath.forSourceCode()))));
        } catch (Exception e) {
            e.printStackTrace();
        }
       return Optional.empty();
    }

    public static TestSourceCode empty() {
        return new TestSourceCode("");
    }

    public TestMethodSourceCode extract(Method method) {
        String sourceSubStringFromMethodName = sourceCode.substring(sourceCode.indexOf(method.getName()));
        String methodSourceCode = recursiveExtracting(sourceSubStringFromMethodName.split("\n"), 0, 0, false);
        return new TestMethodSourceCode(methodSourceCode);
    }

    //TODO: tidy / refactor
    private String recursiveExtracting(String[] arr, int line, int bc, boolean ob) {
        if (ob && bc <= 0) {
            return "";
        }

        if (arr[line].contains("{")) {
            if (!ob) ob = true;
            return arr[line].strip() + "\n" + recursiveExtracting(arr, line + 1, bc + 1, ob);
        }

        if (arr[line].contains("}")) {
            return arr[line].strip() + "\n" + recursiveExtracting(arr, line + 1, bc - 1, ob);
        }
        return arr[line].strip() + "\n" + recursiveExtracting(arr, line + 1, bc, ob);
    }

    public String asString() {
        return sourceCode;
    }
}