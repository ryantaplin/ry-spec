package extension.internal.domain.resources;

import extension.defaults.DefaultTestState;
import extension.TestState;
import org.junit.jupiter.api.Test;

public class StubClassWithDefaultTestState {

    private final TestState someState = new DefaultTestState();

    @Test
    void testMethod() {

    }

}
