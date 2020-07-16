package extension.report;

import extension.report.parser.ReportGenerator;
import extension.test.TestPath;
import extension.test.TestSpecimen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class ReportFileWriter {

    private static final String BASE_REPORT_LOCATION = "target";

    //TODO: make this a property that can be configured
    private static final String DEFAULT_REPORTS_LOCATION = "reports";

    private final ReportGenerator reportGenerator;

    //TODO: abstract the report generation away from the file writer. -> maybe wrap the String result to Report and pass in.
    public ReportFileWriter(ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    public void write(TestSpecimen specimen) {
        File file = createFileIfNotPresent(getReportPathForSpecimen(specimen.getTestPath()));
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(reportGenerator.generateForSpecimen(specimen));
        } catch (IOException e) {
            e.printStackTrace(); //TODO: better exception handling
        }
    }

    private String getReportPathForSpecimen(TestPath path) {
        String result = String.format("%s/%s/%s", BASE_REPORT_LOCATION, DEFAULT_REPORTS_LOCATION, path.forReport());
        System.out.println(result);
        return result;
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
}
