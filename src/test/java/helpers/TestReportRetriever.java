package helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static java.nio.file.Files.readAllLines;

public class TestReportRetriever {

    private Path reportPath;

    private TestReportRetriever(Path reportPath) {
        this.reportPath = reportPath;
    }

    public static TestReportRetriever getReport(String fileName) {
        return Optional.of(Paths.get("target/reports", String.format("%s.html", fileName)))
                .filter(path -> Files.exists(path))
                .map(TestReportRetriever::new)
                .orElse(null);
    }

    public String asString() throws IOException {
        return String.join("\n", readAllLines(reportPath));
    }
}