package extension.test;

import extension.helpers.TestMethodExtractor;
import extension.test.state.TestState;

import java.util.*;

public class TestSpecimen {

    private final String classPath;
    private final HashMap<String, TestMethodData> methodDataMap = new HashMap<>();

    private TestSpecimen(Class<?> specimen) {
        this.classPath = TestPath.forClass(specimen).asString();
        for (TestMethodData testMethodDataEntry : TestMethodExtractor.getTestMethods(specimen)) {
            methodDataMap.put(testMethodDataEntry.getName(), testMethodDataEntry);
        }
    }

    public static TestSpecimen initializeForClass(Class<?> specimen) {
        return new TestSpecimen(specimen);
    }

    public void updateTestMethodResult(String testName, TestResult testResult) {
        Optional.ofNullable(methodDataMap.get(testName))
                .ifPresent(x -> x.updateResult(testResult));
    }

    public String getClassPath() {
        return classPath;
    }

    public List<TestMethodData> getTestMethodDataList() {
        return new ArrayList<>(methodDataMap.values());
    }

    public void updateTestMethodState(String testName, TestState state) {
            Optional.ofNullable(methodDataMap.get(testName))
                    .ifPresent(x -> x.updateState(state));
    }
}
