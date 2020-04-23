package extension;

import extension.report.ReportGenerator;
import extension.report.builder.ReportBuilder;
import extension.report.parser.ReportToHtmlParser;
import extension.report.parser.TestSourceCodeToHtmlParser;
import extension.report.parser.helper.CamelCaseSplitter;
import extension.report.parser.helper.SentenceFormatter;
import extension.report.parser.helper.SourceCodeParser;
import extension.test.TestMethodData;
import extension.test.TestPath;
import extension.test.TestResult;
import extension.test.TestSourceCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static extension.test.TestResult.FAILED;
import static extension.test.TestResult.PASSED;

//TODO: make this abstract? - so user must implement their own
public class ReportExtension implements Extension, BeforeAllCallback, AfterEachCallback, AfterAllCallback {

    private final CamelCaseSplitter camelCaseSplitter = new CamelCaseSplitter();
    private final SourceCodeParser sourceCodeParser = new SourceCodeParser();
    private final SentenceFormatter sentenceFormatter = new SentenceFormatter();
    private final TestSourceCodeToHtmlParser sourceCodeToHtmlParser = new TestSourceCodeToHtmlParser(
            sourceCodeParser, camelCaseSplitter, sentenceFormatter);

    private final ReportToHtmlParser reportParser = new ReportToHtmlParser(camelCaseSplitter, sourceCodeToHtmlParser);
    private final ReportGenerator reportGenerator = new ReportGenerator(reportParser);

    private ReportBuilder reportBuilder;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Class<?> testClass = context.getTestClass().orElseThrow(() -> new Exception("TODO: Exception")); //TODO: Exception

        TestPath path = TestPath.getForClass(testClass.getName());
        TestSourceCode sourceCode = TestSourceCode.read(path);

        //TODO: extract/tidy
        List<TestMethodData> initTestMethodData = Arrays.stream(testClass.getDeclaredMethods())
                .filter(x -> Optional.ofNullable(x.getAnnotation(Test.class)).isPresent())
                .map(method -> new TestMethodData(method.getName(), sourceCode.extract(method), TestResult.NOT_RUN))
                .collect(Collectors.toList());

        this.reportBuilder = ReportBuilder.init(getClassName(context), initTestMethodData);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        String testName = context.getDisplayName().split("\\(")[0];
        TestResult testResult = context.getExecutionException().map(x -> FAILED).orElse(PASSED);
        reportBuilder.updateTestMethodResult(testName, testResult);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        reportGenerator.generate(reportBuilder);
    }

    private String getClassName(ExtensionContext context) throws Exception {
        //TODO: replace the exception with something more suitable.
        return context.getTestClass()
                .map(x -> x.getName().replaceAll("\\.", "\\/"))
                .orElseThrow(() -> new Exception("The class name could not be extracted..."));
    }
}
