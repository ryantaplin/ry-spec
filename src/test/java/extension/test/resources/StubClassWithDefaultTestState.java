package extension.test.resources;

import extension.test.state.DefaultTestState;
import extension.test.state.TestState;
import org.junit.jupiter.api.Test;

public class StubClassWithDefaultTestState {

    private final TestState someState = new DefaultTestState();

    @Test
    void testMethod() {

    }

}
