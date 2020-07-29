package extension.internal.domain;

import extension.internal.domain.test.InternalPath;
import extension.internal.domain.test.method.MethodData;
import extension.internal.domain.test.method.Result;
import extension.internal.extractor.MethodExtractor;
import extension.TestState;

import java.util.*;

public class TestSpecimen {

    private final InternalPath testPath;
    private final HashMap<String, MethodData> methodDataMap = new HashMap<>();

    private TestSpecimen(Class<?> specimen) {
        this.testPath = InternalPath.forClass(specimen);
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
