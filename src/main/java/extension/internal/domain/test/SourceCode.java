package extension.internal.domain.test;

import extension.internal.domain.test.method.MethodSourceCode;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.Optional;

public final class SourceCode {

    private final String sourceCode;

    SourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public static Optional<SourceCode> read(InternalPath testPath) {
        try {
            return Optional.of(new SourceCode(String.join("\n", Files.readAllLines(testPath.forSourceCode()))));
        } catch (Exception e) {
            e.printStackTrace();
        }
       return Optional.empty();
    }

    public static SourceCode empty() {
        return new SourceCode("");
    }

    public MethodSourceCode extract(Method method) {
        String sourceSubStringFromMethodName = sourceCode.substring(sourceCode.indexOf(method.getName()));
        String methodSourceCode = recursiveExtracting(sourceSubStringFromMethodName.split("\n"), 0, 0, false);
        return new MethodSourceCode(methodSourceCode);
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