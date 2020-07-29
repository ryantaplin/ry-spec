package extension.internal.report;

import extension.ResultGenerator;
import extension.internal.domain.test.InternalPath;
import extension.internal.domain.TestSpecimen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class ReportFileWriter {

    private static final String BASE_REPORT_LOCATION = "target";

    //TODO: make this a property that can be configured
    private static final String DEFAULT_REPORTS_LOCATION = "reports";

    private final ResultGenerator resultGenerator;

    //TODO: abstract the report generation away from the file writer. -> maybe wrap the String result to Report and pass in.
    public ReportFileWriter(ResultGenerator resultGenerator) {
        this.resultGenerator = resultGenerator;
    }

    public void write(TestSpecimen specimen) {
        File file = createFileIfNotPresent(getReportPathForSpecimen(specimen.getTestPath()));
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(resultGenerator.generateForSpecimen(specimen));
        } catch (IOException e) {
            e.printStackTrace(); //TODO: better exception handling
        }
    }

    private String getReportPathForSpecimen(InternalPath path) {
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
