package extension.report.parser.html.element;

import extension.report.parser.html.HtmlContent;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

class HtmlElementBuilderTest {

    @Test
    void name() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        HtmlElementBuilder.elementBuilderFor(DivElement.class)
                .withContent(HtmlContent.content(""))
                .build();
    }
}