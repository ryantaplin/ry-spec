package extension.report;

import extension.report.builder.ReportBuilder;
import extension.report.parser.ReportToHtmlParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class ReportGenerator {

    private static final String BASE_REPORT_LOCATION = "target";
    private static final String DEFAULT_REPORTS_LOCATION = "reports";

    private final ReportToHtmlParser reportToHtmlParser;

    public ReportGenerator(ReportToHtmlParser reportToHtmlParser) {
        this.reportToHtmlParser = reportToHtmlParser;
    }

    public void generate(ReportBuilder report) {
        File file = createFileIfNotPresent(String.format("%s/%s/%s.html", BASE_REPORT_LOCATION, getReportsLocation(), report.getClassPath()));
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(reportToHtmlParser.parse(report));
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
