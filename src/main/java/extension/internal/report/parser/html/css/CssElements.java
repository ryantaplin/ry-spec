package extension.internal.report.parser.html.css;

import java.util.*;
import java.util.stream.Collectors;

public class CssElements {

    private final List<CssElement> cssElements;

    private CssElements(List<CssElement> elements) {
        this.cssElements = elements;
    }

    public static CssElements css(CssElement... cssElements) {
        return new CssElements(Arrays.asList(cssElements));
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
        CssElements that = (CssElements) o;
        return Objects.equals(cssElements, that.cssElements);
    }
}
