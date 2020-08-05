package extension.internal.report.parser.html.script;

public class CollapseSiblingsFunction {
    /* TODO: Regretting creating html template now... :|
        better way to do this..? refactor into .js file and read file in? maybe scrap the custom html objects
    */
    public static String collapseSiblingsFunctionAsString() {
        return "function collapseSiblingsFunction(event) {\n" +
                "    var element = event.parentElement.getElementsByClassName(\"collapsibleContent\")[0];\n" +
                "    element.classList.toggle(\"active\");\n" +
                "    if (element.style.display === \"block\") {\n" +
                "        element.style.display = \"none\";\n" +
                "    } else {\n" +
                "        element.style.display = \"block\";\n" +
                "    }\n" +
                "}";
    }
}
