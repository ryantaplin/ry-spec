package extension.report.parser;

import extension.report.builder.ReportBuilder;
import extension.report.parser.html.HtmlValue;
import test.TestMethodData;

import java.util.List;
import java.util.stream.Collectors;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.HtmlTemplateBuilder.htmlTemplate;
import static extension.report.parser.html.element.DivElement.div;

public class ReportToHtmlParser implements ReportParser {

    public String parse(ReportBuilder report) {
        HtmlValue pageTitle = formatPageTitle(report.getClassPath());
        return htmlTemplate()
                .withTitle(pageTitle.asString())
                .withElement(div(pageTitle))
                .withElement(div(formatTestContent(report.getTestMethodData())))
                .build();
    }

    private HtmlValue formatTestContent(List<TestMethodData> testMethodData) {
        return div(
                content(
                        testMethodData.stream()
                                .map(e -> testBox(e).asString())
                                .collect(Collectors.joining())
                ));
    }

    private HtmlValue testBox(TestMethodData t) {
        return div(
                div(content(t.getName() + " : " + t.getResult().toString())),
                div(content(t.getSourceCode().asString()))
        );
    }

    private HtmlValue formatPageTitle(String title) {
        String[] split = title.split("/");
        return content(String.format("%s", split[split.length - 1]));
    }

}
