package extension.report.builder;

import extension.test.TestMethodData;
import extension.test.TestResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportBuilder {

    private final String classPath;
    private final HashMap<String, TestMethodData> methodDataMap = new HashMap<>();

    private ReportBuilder(String testClass, List<TestMethodData> testMethodData) {
        this.classPath = testClass;
        for (TestMethodData testMethodDataEntry : testMethodData) {
            methodDataMap.put(testMethodDataEntry.getName(), testMethodDataEntry);
        }
    }

    public static ReportBuilder init(String testClass, List<TestMethodData> testMethodData) {
        return new ReportBuilder(testClass, testMethodData);
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
}
