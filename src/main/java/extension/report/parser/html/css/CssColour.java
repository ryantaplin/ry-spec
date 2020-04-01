package extension.report.parser.html.css;

public enum CssColour {
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