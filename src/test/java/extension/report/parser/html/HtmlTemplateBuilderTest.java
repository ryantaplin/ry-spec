package extension.report.parser.html;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static extension.report.parser.html.HtmlContent.content;
import static extension.report.parser.html.HtmlTemplateBuilder.htmlTemplate;
import static extension.report.parser.html.element.DivElement.div;
import static org.assertj.core.api.Assertions.assertThat;

class HtmlTemplateBuilderTest {

    @Test
    void resultContainsDocTypeTag() {
        assertThat(htmlTemplate().build()).contains("<!DOCTYPE html>");
    }

    @Test
    void resultContainsHtmlTagsWithLangAttribute() {
        assertThat(htmlTemplate().build()).contains("<html lang=\"en\" style=\"height: 98%\">").contains("</html>");
    }

    @Test
    void resultContainsHeadTags() {
        assertThat(htmlTemplate().build()).contains("<head>").contains("</head>");
    }

    @Test
    void resultContainsSpecifiedTitleInputInTitleTag() {
        HtmlTemplateBuilder template = htmlTemplate().withTitle("SomeInputTitle");
        assertThat(template.build()).contains("<title>SomeInputTitle</title>");
    }

    @Test
    void resultsContainsMetaTagWithUTF8CharsetAttribute() {
        assertThat(htmlTemplate().build()).contains("<meta charset=\"UTF-8\"");
    }

    @Test
    void resultContainsBodyTags() {
        assertThat(htmlTemplate().build()).contains("<body style=\"border: solid 1px black; height: 100%\">").contains("</body>");
    }

    @Test
    void resultContainsSpecifiedElementInput() {
        HtmlTemplateBuilder template = htmlTemplate().withElement(div(content("SomeDivContent")));
        assertThat(template.build()).contains("<div>SomeDivContent</div>");
    }

    @Test
    void resultContainsAllElements() {
        HtmlTemplateBuilder template = htmlTemplate().withElements(Arrays.asList(div(content("one")),div(content("two"))));
        assertThat(template.build()).contains("<div>one</div><div>two</div>");
    }
}