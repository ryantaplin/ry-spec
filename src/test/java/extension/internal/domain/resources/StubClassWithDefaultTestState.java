package extension.internal.domain.resources;

import extension.ReportGenerator;
import extension.defaults.DefaultTestState;
import extension.TestState;
import org.junit.jupiter.api.Test;

@ReportGenerator
public class StubClassWithDefaultTestState {

    private final TestState someState = new DefaultTestState();

    @Test
    void testMethod() {

    }

}
