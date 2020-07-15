package extension.report;

import extension.report.parser.ReportGenerator;
import extension.test.TestSpecimen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class ReportFileWriter {

    private static final String BASE_REPORT_LOCATION = "target";
    private static final String DEFAULT_REPORTS_LOCATION = "reports";

    private final ReportGenerator reportGenerator;

    //TODO: abstract the report generation away from the file writer. -> maybe wrap the String result to Report and pass in.
    public ReportFileWriter(ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    public void write(TestSpecimen specimen) {
        File file = createFileIfNotPresent(getReportPathForSpecimen(specimen.getClassPath()));
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(reportGenerator.generateForSpecimen(specimen));
        } catch (IOException e) {
            e.printStackTrace(); //TODO: better exception handling
        }
    }

    private String getReportPathForSpecimen(String specimenClassPath) {
        String result = String.format("%s/%s/%s.html", BASE_REPORT_LOCATION, getReportsLocation(), specimenClassPath);
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

    private static String getReportsLocation() {
        //TODO: make this a property that can be configured
        return DEFAULT_REPORTS_LOCATION;
    }
}
