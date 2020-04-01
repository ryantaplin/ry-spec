package extension.report.parser.html.element;

import extension.report.parser.html.HtmlValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HtmlElementBuilder {


    private static Class<? extends HtmlElement> elementClass;
    private List<HtmlValue> values = new ArrayList<>();

    public HtmlElementBuilder(Class<? extends HtmlElement> elementClass) {
        this.elementClass = elementClass;
    }

    public static HtmlElementBuilder elementBuilderFor(Class<? extends HtmlElement> element) {
        HtmlElementBuilder.elementClass = element;
        return new HtmlElementBuilder(element);
    }

    public HtmlElementBuilder withContent(HtmlValue value) {
        this.values.add(value);
        return this;
    }

    public HtmlElement build() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method elementMethod = Arrays.asList(elementClass.getMethods()).stream()
                .filter(e -> e.getName().equals("div"))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("TODO: Exception"));
        return (HtmlElement)elementMethod.invoke(values.toArray());
    }
}
