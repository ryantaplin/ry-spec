package extension;

import extension.helpers.TestAnnotationExtractor;
import extension.helpers.TestStateExtractor;
import extension.report.ReportFileWriter;
import extension.report.parser.HtmlReportGenerator;
import extension.report.parser.TestSpecimenToHtmlWorker;
import extension.report.parser.helper.CamelCaseSplitter;
import extension.report.parser.helper.ForbiddenCharacterFilter;
import extension.report.parser.helper.SentenceFormatter;
import extension.report.parser.helper.SourceCodeParser;
import extension.report.parser.html.css.helper.TestContentCssHelper;
import extension.report.parser.html.parser.TestHeaderToHtmlParser;
import extension.report.parser.html.parser.TestSourceCodeToHtmlParser;
import extension.report.parser.html.parser.TestStateToHtmlParser;
import extension.report.parser.html.parser.teststate.CapturedInteractionsToHtmlParser;
import extension.report.parser.html.parser.teststate.InterestingGivensToHtmlParser;
import extension.test.TestSpecimen;
import org.junit.jupiter.api.extension.*;

import static extension.test.TestResult.FAILED;
import static extension.test.TestResult.PASSED;

class ReportExtension implements Extension, BeforeAllCallback, AfterEachCallback, AfterAllCallback {

    private final SentenceFormatter sentenceFormatter = new SentenceFormatter(new CamelCaseSplitter());
    private final SourceCodeParser sourceCodeParser = new SourceCodeParser(new ForbiddenCharacterFilter(), sentenceFormatter);

    private final TestContentCssHelper testContentCssHelper = new TestContentCssHelper();
    private final ReportFileWriter reportToFileWriter = new ReportFileWriter(
            new HtmlReportGenerator(
                    sentenceFormatter,
                    new TestSpecimenToHtmlWorker(
                            new TestHeaderToHtmlParser(sentenceFormatter, testContentCssHelper),
                            new TestSourceCodeToHtmlParser(sourceCodeParser, testContentCssHelper),
                            new TestStateToHtmlParser(
                                    new InterestingGivensToHtmlParser(testContentCssHelper),
                                    new CapturedInteractionsToHtmlParser(testContentCssHelper),
                                    testContentCssHelper
                            ),
                            testContentCssHelper
                    )
            )
    );

    private TestSpecimen testSpecimen;
    private ReportGenerator annotation;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        this.annotation = context.getTestClass().map(TestAnnotationExtractor::getReportAnnotation).get();
        this.testSpecimen = context.getTestClass()
                .map(TestSpecimen::initializeForClass)
                .orElseThrow(() -> new Exception("TODO:"));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        String name = context.getDisplayName().split("\\(")[0];
        testSpecimen.updateTestMethodResult(name, context.getExecutionException().map(x -> FAILED).orElse(PASSED));
        context.getTestInstance()
                .flatMap(TestStateExtractor::getOptionalTestStateFrom)
                .ifPresent(state -> testSpecimen.updateTestMethodState(name, state));
    }

    @Override
    public void afterAll(ExtensionContext context) {
        reportToFileWriter.write(testSpecimen);
    }

}
