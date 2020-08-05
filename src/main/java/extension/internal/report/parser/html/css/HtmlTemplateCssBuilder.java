package extension.internal.report.parser.html.css;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlTemplateCssBuilder {

    private List<NamedCssAttributesBuilder> namedCssAttributes = new ArrayList<>();

    private HtmlTemplateCssBuilder() {
    }

    public static HtmlTemplateCssBuilder cssBuilder() {
        return new HtmlTemplateCssBuilder();
    }

    public HtmlTemplateCssBuilder addCss(NamedCssAttributesBuilder classElements) {
        this.namedCssAttributes.add(classElements);
        return this;
    }

    public String build() {
        return namedCssAttributes.stream()
                .map(NamedCssAttributesBuilder::build)
                .collect(Collectors.joining("\n"));
    }

}