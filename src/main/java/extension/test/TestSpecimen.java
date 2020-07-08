package extension.test;

import extension.helpers.TestMethodExtractor;

import java.util.*;

//TODO: refactor out


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
        if (methodDataMap.containsKey(testName)) {
            methodDataMap.get(testName).updateResult(testResult);
        }
    }

    public String getClassPath() {
        return classPath;
    }

    public List<TestMethodData> getTestMethodData() {
        return new ArrayList<>(methodDataMap.values());
    }

//    public void updateTestMethodInterestings(String name, String interesting) {
//
//    }
}
