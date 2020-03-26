package extension.report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static java.nio.file.Files.readAllLines;

class ReportRetriever {

    private Path reportPath;

    private ReportRetriever(Path reportPath) {
        this.reportPath = reportPath;
    }

    static ReportRetriever getReport(String fileName) {
        return Optional.of(Paths.get("target/reports", String.format("%s.html", fileName)))
                .filter(path -> Files.exists(path))
                .map(ReportRetriever::new)
                .orElse(null);
    }

    String asString() throws IOException {
        return String.join("\n", readAllLines(reportPath));
    }
}