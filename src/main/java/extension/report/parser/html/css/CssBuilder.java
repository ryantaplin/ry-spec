package extension.report.parser.html.css;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CssBuilder {

    private List<CssElement> cssElements = new ArrayList<>();

    private CssBuilder() {
    }

    public static CssBuilder css() {
        return new CssBuilder();
    }

    public CssBuilder backgroundColour(CssColour colour) {
        this.cssElements.add(new CssElement("background-color", colour.getHexValue()));
        return this;
    }

    public CssBuilder fontColour(CssColour colour) {
        this.cssElements.add(new CssElement("color", colour.getHexValue()));
        return this;
    }

    public String asString() {
        if (cssElements.size() <= 0) return "";
        else return "style=\"" + cssElements.stream()
                .map(CssElement::getAttributeWithValue)
                .collect(Collectors.joining("; ")) + "\"";
    }

    public CssBuilder fontSize(int size) {
        this.cssElements.add(new CssElement("font-size", String.format("%spx", size)));
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CssBuilder that = (CssBuilder) o;
        return Objects.equals(cssElements, that.cssElements);
    }
}
