package extension.internal.report.parser.html.css.attribute;

import org.junit.jupiter.api.Test;

import static extension.internal.report.parser.html.css.attribute.CssUserSelectDisabled.*;
import static org.assertj.core.api.Assertions.assertThat;

class CssUserSelectDisabledTest {

    @Test
    void webKitUserSelectDisabledAsString() {
        assertThat(webKitUserSelectDisabled().getAttributeWithValue())
                .isEqualTo("-webkit-user-select:none");
    }

    @Test
    void mozUserSelectDisabledAsString() {
        assertThat(mozUserSelectDisabled().getAttributeWithValue())
                .isEqualTo("-moz-user-select:none");
    }

    @Test
    void msUserSelectDisabledAsString() {
        assertThat(msUserSelectDisabled().getAttributeWithValue())
                .isEqualTo("-ms-user-select:none");
    }
}