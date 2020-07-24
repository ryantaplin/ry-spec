package extension.report.parser.html.css;

import java.util.List;

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
