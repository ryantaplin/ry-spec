package extension.internal.report.parser.html.css.attribute;

public class CssFontWeight extends CssElement {

    private CssFontWeight(String attribute, String value) {
        super(attribute, value);
    }

    public static CssFontWeight fontWeight(CssFontWeightType borderCollapseType) {
        return new CssFontWeight("font-weight", borderCollapseType.getValue());
    }
}
