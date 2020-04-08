package extension.report.parser.html.css;

public enum CssColour {
    BLUE("70e1e1"),
    WHITE("FFFFFF"),
    BLACK("000000");

    private String hex;

    CssColour(String hex) {
        this.hex = hex;
    }

    public String getHexValue() {
        return String.format("#%s", hex);
    }
}