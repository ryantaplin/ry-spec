package extension.internal.domain;

import extension.ReportGenerator;
import extension.internal.domain.test.InternalPath;
import extension.internal.domain.test.method.MethodData;
import extension.internal.domain.test.method.Result;
import extension.internal.extractor.AnnotationExtractor;
import extension.internal.extractor.MethodExtractor;
import extension.TestState;

import java.lang.annotation.Annotation;
import java.util.*;

public class TestSpecimen {

    private final InternalPath testPath;
    private final HashMap<String, MethodData> methodDataMap = new HashMap<>();

    private final ReportGenerator reportProperties;
    private final List<Annotation> optionalAnnotations;

    private TestSpecimen(Class<?> specimen) {
        this.testPath = InternalPath.forClass(specimen);
        this.reportProperties = AnnotationExtractor.getReportAnnotation(specimen);
        this.optionalAnnotations = Collections.emptyList(); //TODO: add Extractors for Note, LinkingNote, IssueTracker.
        for (MethodData methodDataEntry : MethodExtractor.getTestMethods(specimen)) {
            methodDataMap.put(methodDataEntry.getName(), methodDataEntry);
        }
    }

    public static TestSpecimen initializeForClass(Class<?> specimen) {
        return new TestSpecimen(specimen);
    }

    public void updateTestMethodResult(String testName, Result result) {
        Optional.ofNullable(methodDataMap.get(testName))
                .ifPresent(x -> x.updateResult(result));
    }

    public InternalPath getTestPath() {
        return testPath;
    }

    public List<MethodData> getTestMethodDataList() {
        return new ArrayList<>(methodDataMap.values());
    }

    public void updateTestMethodState(String testName, TestState state) {
            Optional.ofNullable(methodDataMap.get(testName))
                    .ifPresent(x -> x.updateState(state));
    }
}
