package extension.internal.report;

import extension.internal.domain.test.InternalPath;
import extension.internal.domain.TestSpecimen;
import extension.ResultGenerator;
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

    private final ResultGenerator parser = mock(ResultGenerator.class);
    private final ReportFileWriter testClass = new ReportFileWriter(parser);

    private final TestSpecimen testSpecimen = mock(TestSpecimen.class);

    @BeforeEach
    void setUp() {
        //TODO: cleanup incase residual is left from previous test runs

        final InternalPath testPath = mock(InternalPath.class);
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