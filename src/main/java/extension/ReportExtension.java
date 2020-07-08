package extension;

import extension.report.HtmlReportGenerator;
import extension.test.TestSpecimen;
import extension.report.parser.ReportToHtmlParser;
import extension.report.parser.TestSourceCodeToHtmlParser;
import extension.report.parser.helper.CamelCaseSplitter;
import extension.report.parser.helper.SentenceFormatter;
import extension.report.parser.helper.SourceCodeParser;
import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.helpers.TestStateExtractor;
import org.junit.jupiter.api.extension.*;

import static extension.test.TestResult.FAILED;
import static extension.test.TestResult.PASSED;

public class ReportExtension implements Extension, BeforeAllCallback, AfterEachCallback, AfterAllCallback {

    private final CamelCaseSplitter camelCaseSplitter = new CamelCaseSplitter();
    private final SourceCodeParser sourceCodeParser = new SourceCodeParser();
    private final SentenceFormatter sentenceFormatter = new SentenceFormatter();
    private final TestContentCssHelper testContentCssHelper = new TestContentCssHelper();

    private final TestSourceCodeToHtmlParser sourceCodeToHtmlParser = new TestSourceCodeToHtmlParser(
            sourceCodeParser, camelCaseSplitter, sentenceFormatter, testContentCssHelper);

    private final ReportToHtmlParser reportParser = new ReportToHtmlParser(camelCaseSplitter, sourceCodeToHtmlParser);
    private final HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator(reportParser);

    private TestSpecimen testSpecimen;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        this.testSpecimen = context.getTestClass()
                .map(TestSpecimen::initializeForClass)
                .orElseThrow(() -> new Exception("TODO:"));
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        String name = context.getDisplayName().split("\\(")[0];
        testSpecimen.updateTestMethodResult(name, context.getExecutionException().map(x -> FAILED).orElse(PASSED));
        context.getTestInstance()
                .flatMap(TestStateExtractor::getOptionalTestStateFrom);
//                .ifPresent(state -> testSpecimen.updateTestMethodInterestings(name, state.getInterestings()));
    }

    @Override
    public void afterAll(ExtensionContext context) {
        htmlReportGenerator.generate(testSpecimen);
    }

}
