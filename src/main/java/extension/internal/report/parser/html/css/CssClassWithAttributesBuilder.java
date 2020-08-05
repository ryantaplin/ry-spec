package extension.internal.report.parser.html.css;

import extension.internal.report.parser.html.css.attribute.CssElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class CssClassWithAttributesBuilder implements NamedCssAttributesBuilder {

    private final List<String> className;
    private List<CssElement> attributes = new ArrayList<>();

    protected CssClassWithAttributesBuilder(List<String> className) {
        this.className = className;
    }

    public CssClassWithAttributesBuilder withAttribute(CssElement cssElement) {
        this.attributes.add(cssElement);
        return this;
    }

    @Override
    public String build() {
        return format("%s { %s }",
                className.stream()
                        .map(className -> format(".%s", className))
                        .collect(Collectors.joining(", ")),
                attributes.stream()
                        .map(CssElement::getAttributeWithValue)
                        .collect(Collectors.joining("; "))
        );
    }
}