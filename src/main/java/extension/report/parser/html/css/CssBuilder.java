package extension.report.parser.html.css;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CssBuilder {

    private List<CssElement> cssElements = new ArrayList<>();

    private CssBuilder() { }

    public static CssBuilder cssBuilder() {
        return new CssBuilder();
    }

    public CssBuilder withBackgroundColour(CssColour colour) {
        this.cssElements.add(new CssElement("background-color", colour.getHexValue()));
        return this;
    }

    public CssBuilder withFontColour(CssColour colour) {
        this.cssElements.add(new CssElement("color", colour.getHexValue()));
        return this;
    }

    @Override
    public String toString() {
        return cssElements.stream()
                .map(CssElement::getAttributeWithValue)
                .collect(Collectors.joining("; "));
    }
}
