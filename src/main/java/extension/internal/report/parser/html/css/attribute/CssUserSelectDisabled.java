package extension.internal.report.parser.html.css.attribute;

public class CssUserSelectDisabled extends CssElement {

    private CssUserSelectDisabled(String attribute, String value) {
        super(attribute, value);
    }

    public static CssUserSelectDisabled webKitUserSelectDisabled() {
        return new CssUserSelectDisabled("-webkit-user-select", "none");
    }

    public static CssUserSelectDisabled mozUserSelectDisabled() {
        return new CssUserSelectDisabled("-moz-user-select", "none");
    }

    public static CssUserSelectDisabled msUserSelectDisabled() {
        return new CssUserSelectDisabled("-ms-user-select", "none");
    }

}
