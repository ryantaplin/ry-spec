package extension;

import java.util.Optional;

public interface WithCustomRenderers {
    <T> void withCustomRenderer(Class<T> object, CustomRenderer<T> renderer);
    <T> Optional<CustomRenderer<?>> getCustomRendererFor(T object);
}
