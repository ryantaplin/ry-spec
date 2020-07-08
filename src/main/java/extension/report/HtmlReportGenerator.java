package extension.report;

import extension.test.TestSpecimen;
import extension.report.parser.ReportParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class HtmlReportGenerator implements ReportGenerator {

    private static final String BASE_REPORT_LOCATION = "target";
    private static final String DEFAULT_REPORTS_LOCATION = "reports";

    private final ReportParser reportParser;

    public HtmlReportGenerator(ReportParser reportParser) {
        this.reportParser = reportParser;
    }

    public void generate(TestSpecimen report) {
        File file = createFileIfNotPresent(String.format("%s/%s/%s.html", BASE_REPORT_LOCATION, getReportsLocation(), report.getClassPath()));
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(reportParser.parse(report));
        } catch (IOException e) {
            e.printStackTrace(); //TODO: better exception handling
        }
    }

    private static File createFileIfNotPresent(String filePath) {
        File file = new File(filePath);
        try {
            Files.createDirectories(file.toPath().getParent());
        } catch (IOException e) {
            e.printStackTrace(); //TODO: better exception handling
        }
        return file;
    }

    private static String getReportsLocation() {
        //TODO: make this a property that can be configured
        return DEFAULT_REPORTS_LOCATION;
    }
}
