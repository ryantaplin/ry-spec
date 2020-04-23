package extension.report.parser.html.css;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    public CssBuilder fontSize(int size) {
        String formattedValue = String.format("%spx", size);
        this.cssElements.add(new CssElement("font-size", formattedValue));
        return this;
    }

    public CssBuilder margin(int top, int right, int bottom, int left) {
        String formattedValue = String.format("%spx %spx %spx %spx", top, right, bottom, left);
        this.cssElements.add(new CssElement("margin", formattedValue));
        return this;
    }

    public CssBuilder padding(int top, int right, int bottom, int left) {
        String formattedValue = String.format("%spx %spx %spx %spx", top, right, bottom, left);
        this.cssElements.add(new CssElement("padding", formattedValue));
        return this;
    }

    public CssBuilder border(int width) {
        border(width, null);
        return this;
    }

    public CssBuilder border(int width, CssPosition position) {
        String formattedValue = String.format("%spx solid black", width);
        String formattedAttribute = attributePositionHelperFor("border", position);
        this.cssElements.add(new CssElement(formattedAttribute, formattedValue));
        return this;
    }

    private String attributePositionHelperFor(String attribute, CssPosition location) {
        return Optional.ofNullable(location)
                .map(position -> position.toString().toLowerCase())
                .map(position -> String.format("%s-%s", attribute, position))
                .orElse(attribute);

    }

    public String asString() {
        if (cssElements.size() <= 0) return "";
        else return "style=\"" + cssElements.stream()
                .map(CssElement::getAttributeWithValue)
                .collect(Collectors.joining("; ")) + "\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CssBuilder that = (CssBuilder) o;
        return Objects.equals(cssElements, that.cssElements);
    }
}
