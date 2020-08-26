package extension.internal.report.parser.html.css.attribute;

public class CssBorderCollapse extends CssElement {

    private CssBorderCollapse(String attribute, String value) {
        super(attribute, value);
    }

    public static CssBorderCollapse borderCollapse(CssBorderCollapseType borderCollapseType) {
        return new CssBorderCollapse("border-collapse", borderCollapseType.getValue());
    }
}
