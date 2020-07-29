package extension.internal.report.parser.html.css;

public enum CssColour {
    BLUE("70e1e1"),
    WHITE("FFFFFF"),
    BLACK("444444"),
    GREY("EEEEEE");

    private String hex;

    CssColour(String hex) {
        this.hex = hex;
    }

    public String getHexValue() {
        return String.format("#%s", hex);
    }
}