package extension.internal.report.parser.html.css;

import java.util.Arrays;
import java.util.Collections;

public final class CssClassBuilder {

    public static CssClassWithAttributesBuilder cssClasses(String... classNames) {
        return new CssClassWithAttributesBuilder(Arrays.asList(classNames));
    }

    public static CssClassWithAttributesBuilder cssClass(String className) {
        return new CssClassWithAttributesBuilder(Collections.singletonList(className));
    }
}