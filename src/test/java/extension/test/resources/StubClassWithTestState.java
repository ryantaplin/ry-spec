package extension.test.resources;

import extension.test.TestState;

import java.util.Map;

public final class StubClassWithTestState {

    private final TestState someState = new TestState() {
        @Override
        public void putOrAddInteresting(String key, Object value) { }

        @Override
        public Map<String, Object> getInterestings() {
            return null;
        }
    };

}
