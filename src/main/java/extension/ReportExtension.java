package extension;

import extension.internal.domain.TestSpecimen;
import extension.internal.extractor.StateExtractor;
import extension.internal.report.ReportFileWriter;
import extension.internal.report.parser.HtmlResultGenerator;
import extension.internal.report.parser.TestSpecimenToHtmlWorker;
import extension.internal.report.parser.helper.CamelCaseSplitter;
import extension.internal.report.parser.helper.ForbiddenCharacterFilter;
import extension.internal.report.parser.helper.SentenceFormatter;
import extension.internal.report.parser.helper.SourceCodeParser;
import extension.internal.report.parser.html.css.helper.TestContentCssHelper;
import extension.internal.report.parser.html.parser.SourceCodeToHtmlParser;
import extension.internal.report.parser.html.parser.StateToHtmlParser;
import extension.internal.report.parser.html.parser.StringToHtmlHeaderParser;
import extension.internal.report.parser.html.parser.teststate.CapturedInteractionsToHtmlParser;
import extension.internal.report.parser.html.parser.teststate.InterestingGivensToHtmlParser;
import org.junit.jupiter.api.extension.*;

import static extension.internal.domain.test.method.Result.FAILED;
import static extension.internal.domain.test.method.Result.PASSED;

final class ReportExtension implements Extension, BeforeAllCallback, AfterEachCallback, AfterAllCallback {

    private final SentenceFormatter sentenceFormatter = new SentenceFormatter(new CamelCaseSplitter());
    private final SourceCodeParser sourceCodeParser = new SourceCodeParser(new ForbiddenCharacterFilter(), sentenceFormatter);

    private final TestContentCssHelper testContentCssHelper = new TestContentCssHelper();
    private final ReportFileWriter reportToFileWriter = new ReportFileWriter(
            new HtmlResultGenerator(
                    sentenceFormatter,
                    new TestSpecimenToHtmlWorker(
                            new StringToHtmlHeaderParser(sentenceFormatter, testContentCssHelper),
                            new SourceCodeToHtmlParser(sourceCodeParser, testContentCssHelper),
                            new StateToHtmlParser(
                                    new InterestingGivensToHtmlParser(testContentCssHelper),
                                    new CapturedInteractionsToHtmlParser(testContentCssHelper),
                                    testContentCssHelper
                            ),
                            testContentCssHelper
                    )
            )
    );


    //TODO: thinking to refactor the class ->
    // beforeAll -> ClassDataGatherer.init(Class<?> testClass) returns ClassMethodDataMediator
    //  |
    // afterEach -> ClassMethodDataMediator.update(String methodName, TestState state, Result result) returns self
    //  |
    // afterAll ->  ClassMethodDataMediator.finialize() returns ClassDataResult
    //                  |
    //              ClassDataResultToHtmlWriter(ClassDataResult dataResult) void
    private TestSpecimen testSpecimen;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        this.testSpecimen = context.getTestClass()
                .map(TestSpecimen::initializeForClass)
                .orElseThrow(() -> new Exception("TODO:"));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        String name = context.getDisplayName().split("\\(")[0];
//        String annotations = context.getTestMethod().map(x -> x.getAnnotations())
        testSpecimen.updateTestMethodResult(name, context.getExecutionException().map(x -> FAILED).orElse(PASSED));
        context.getTestInstance()
                .flatMap(StateExtractor::getOptionalTestStateFrom)
                .ifPresent(state -> testSpecimen.updateTestMethodState(name, state));
    }

    @Override
    public void afterAll(ExtensionContext context) {
        reportToFileWriter.write(testSpecimen);
    }

}
