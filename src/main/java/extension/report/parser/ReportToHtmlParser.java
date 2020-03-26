package extension.report.parser;

import extension.report.builder.ReportBuilder;
import test.TestMethodData;

import java.util.List;
import java.util.stream.Collectors;

public class ReportToHtmlParser {

    public String parse(ReportBuilder report) {
        return "<div>" + formatPageTitle(report.getClassPath()) + "</div>" +
                "<div>" + formatPageIndex(report.getTestMethodData()) + "</div>" +
                "<div>" + formatTestContent(report.getTestMethodData()) + "</div>";
    }

    private String formatTestContent(List<TestMethodData> testMethodData) {
        return "<div>" + testMethodData.stream().map(this::testBox).collect(Collectors.joining()) + "</div>";
    }

    private String testBox(TestMethodData t) {
        return "<div>" +
                    "<div>" + t.getName() + " : " + t.getResult().toString() + "</div>" +
                    "<div>" + t.getSourceCode().asString() + "</div>" +
                "</div>";
    }

    private String formatPageIndex(List<TestMethodData> testMethodData) {
        return "<ul>" +
                testMethodData.stream().map(t -> String.format("<li>%s</li>", t.getName())).collect(Collectors.joining()) +
                "</ul>";
    }

    public String formatPageTitle(String title) {
        String[] split = title.split("/");
        return String.format("%s", split[split.length - 1]);
    }

}
