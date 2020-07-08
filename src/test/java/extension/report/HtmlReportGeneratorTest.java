package extension.report;

import extension.test.TestSpecimen;
import extension.report.parser.ReportParser;
import extension.test.resources.StubClass;
import helpers.TestReportRetriever;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HtmlReportGeneratorTest {

    private final ReportParser parser = mock(ReportParser.class);
    private final HtmlReportGenerator testClass = new HtmlReportGenerator(parser);

    @BeforeEach
    void setUp() {
        when(parser.parse(any(TestSpecimen.class))).thenReturn("testClass");
    }

    @AfterAll
    static void afterAll() {
        //TODO: cleanup stubbed folder/classes so it's not outputted in the final report.
    }

    @Test
    void reportDirectoryAndFileIsCreatedIfItDoesNotExist() throws IOException {
        testClass.generate(TestSpecimen.initializeForClass(StubClass.class));

        assertThat(TestReportRetriever.getReport("unknownDirectory/unknownFileName")).isNotNull();
        assertThat(TestReportRetriever.getReport("stubbed/testClass").asString()).contains("testClass");
    }
}