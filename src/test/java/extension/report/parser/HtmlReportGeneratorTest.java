package extension.report.parser;

import extension.report.parser.helper.SentenceFormatter;
import extension.test.TestPath;
import extension.test.TestSpecimen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.List;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.element.DivElement.div;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HtmlReportGeneratorTest {

    private final TestSpecimen testSpecimen = mock(TestSpecimen.class);
    private final TestPath testPath = mock(TestPath.class);

    private final SentenceFormatter sentenceFormatter = mock(SentenceFormatter.class);
    private final TestSpecimenToHtmlWorker worker = mock(TestSpecimenToHtmlWorker.class);

    private final HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator(sentenceFormatter, worker);

    @BeforeEach
    void setUp() {
        when(testPath.asRawString()).thenReturn("stubTestClassPath");
        when(testSpecimen.getTestPath()).thenReturn(testPath);
        final Answer<String> withInput = invocation -> {
            Object[] args = invocation.getArguments();
            return (String) args[0];
        };
        when(sentenceFormatter.format(anyString())).thenAnswer(withInput);
    }

    @Test
    void reportParserIncludesTitleWithClassName() {
        String actual = htmlReportGenerator.generateForSpecimen(testSpecimen);
        assertThat(actual).contains("<title>stubTestClassPath</title>");
    }

    @Test
    void reportParserIncludesDivWithTestClassName() {
        String actual = htmlReportGenerator.generateForSpecimen(testSpecimen);
        assertThat(actual).contains("<div>stubTestClassPath</div>");
    }

    @Test
    void includesElementReturnedByWorker() {
        when(worker.parse(any())).thenReturn(List.of(div(content("someContent"))));
        String actual = htmlReportGenerator.generateForSpecimen(testSpecimen);
        assertThat(actual).contains("<div>someContent</div>");
    }

    @Test
    void includesMultipleElementsReturnedByWorker() {
        when(worker.parse(any())).thenReturn(List.of(div(content("someContent")), div(content("moreContent"))));
        String actual = htmlReportGenerator.generateForSpecimen(testSpecimen);
        assertThat(actual).contains("<div>someContent</div><div>moreContent</div>");
    }
}