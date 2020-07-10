package extension.test.resources;

import extension.test.TestState;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class StubClassWithTestState {

    private final TestState someState = new TestState() {
        @Override
        public void putOrAddInteresting(String key, Object value) { }

        @Override
        public Map<String, Object> getInterestings() {
            return null;
        }
    };

    @Test
    void testMethod() {

    }

}
