package extension.report;

import extension.test.TestPath;
import extension.test.TestSpecimen;
import extension.report.parser.ReportGenerator;
import helpers.TestReportRetriever;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportFileWriterTest {

    private final ReportGenerator parser = mock(ReportGenerator.class);
    private final ReportFileWriter testClass = new ReportFileWriter(parser);

    private final TestSpecimen testSpecimen = mock(TestSpecimen.class);

    @BeforeEach
    void setUp() {
        //TODO: cleanup incase residual is left from previous test runs

        final TestPath testPath = mock(TestPath.class);
        when(testPath.forReport()).thenReturn("stubbed/testClass.html");
        when(testSpecimen.getTestPath()).thenReturn(testPath);
        when(parser.generateForSpecimen(any(TestSpecimen.class))).thenReturn("testClass");
    }

    @AfterAll
    static void afterAll() {
        //TODO: cleanup stubbed folder/classes so it's not outputted in the final report.
    }

    @Test
    void reportDirectoryAndFileIsCreatedIfItDoesNotExist() throws IOException {
        testClass.write(testSpecimen);
        assertThat(TestReportRetriever.getReport("stubbed/testClass").asString())
                .contains("testClass");
    }
}