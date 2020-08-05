package extension.internal.report.parser;

import extension.ResultGenerator;
import extension.internal.report.parser.helper.SentenceFormatter;
import extension.internal.report.parser.html.element.DivElement;
import extension.internal.domain.TestSpecimen;

import static extension.internal.report.parser.html.HtmlContent.content;
import static extension.internal.report.parser.html.HtmlTemplateBuilder.htmlTemplate;
import static extension.internal.report.parser.html.css.attribute.CssBorder.border;
import static extension.internal.report.parser.html.element.DivElement.div;

public class HtmlResultGenerator implements ResultGenerator {

    private SentenceFormatter sentenceFormatter;
    private TestSpecimenToHtmlWorker testSpecimenToHtmlWorker;

    public HtmlResultGenerator(SentenceFormatter sentenceFormatter, TestSpecimenToHtmlWorker testSpecimenToHtmlWorker) {
        this.sentenceFormatter = sentenceFormatter;
        this.testSpecimenToHtmlWorker = testSpecimenToHtmlWorker;
    }

    public String generateForSpecimen(TestSpecimen specimen) {
        String reportPageName = formatPageTitle(specimen.getTestPath().asRawString());
        return htmlTemplate()
                .withTitle(reportPageName)
                .withBodyElement(titleDiv(reportPageName))
                .withBodyElements(testSpecimenToHtmlWorker.parse(specimen.getTestMethodDataList()))
                .build();
    }

    private DivElement titleDiv(String pageTitle) {
        return div(div(content(pageTitle))).withClassName("pageTitle");
    }

    private String formatPageTitle(String title) {
        String[] split = title.split("/");
        return String.format("%s", sentenceFormatter.format(split[split.length - 1]));
    }
}