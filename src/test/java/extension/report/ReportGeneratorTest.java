package extension.report;

import extension.report.builder.ReportBuilder;
import extension.report.parser.ReportToHtmlParser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportGeneratorTest {

    private final ReportToHtmlParser parser = new ReportToHtmlParser();
    private final ReportGenerator testClass = new ReportGenerator(parser);

    @AfterAll
    static void afterAll() {
        //TODO: cleanup stubbed folder/classes so it's not outputted in the final report.
    }

    @Test
    void reportDirectoryAndFileIsCreatedIfItDoesNotExist() {
        testClass.generate(ReportBuilder.init("unknownDirectory/unknownFileName", emptyList()));

        assertThat(ReportRetriever.getReport("unknownDirectory/unknownFileName")).isNotNull();
    }

    @Test
    void reportDirectory() throws IOException {
        testClass.generate(ReportBuilder.init("stubbed/testClass", emptyList()));

        assertThat(ReportRetriever.getReport("stubbed/testClass").asString()).contains("testClass");
    }
}