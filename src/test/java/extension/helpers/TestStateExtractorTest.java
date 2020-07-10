package extension.helpers;

import extension.test.TestState;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class TestStateExtractorTest {

    @Test
    void returnsTestStateWhenClassHasAPrivatelyDeclaredFinalTestState() {
        Optional<TestState> optionalTestState = TestStateExtractor.getOptionalTestStateFrom(new StubClassWithTestState());
        assertThat(optionalTestState).isNotEmpty();
    }

    @Test
    void returnsTestStateWhenExistsInClassHierarchy() {
        Optional<TestState> optionalTestState = TestStateExtractor.getOptionalTestStateFrom(new StubClassWithNestedTestState());
        assertThat(optionalTestState).isNotEmpty();
    }

    @Test
    void returnsEmptyWhenClassDoesNotHaveTestState() {
        Optional<TestState> optionalTestState = TestStateExtractor.getOptionalTestStateFrom(new StubClassWithNoTestState());
        assertThat(optionalTestState).isEmpty();
    }

    @Test
    void returnsEmptyWhenClassHasNullTestState() {
        Optional<TestState> optionalTestState = TestStateExtractor.getOptionalTestStateFrom(new StubEmptyTestStateClass());
        assertThat(optionalTestState).isEmpty();
    }

    @Test
    void returnsEmptyWhenInputIsNull() {
        Optional<TestState> optionalTestState = TestStateExtractor.getOptionalTestStateFrom(null);
        assertThat(optionalTestState).isEmpty();
    }

    @Test
    void returnsEmptyWhenClassDoesNotExistInTestDirectory() {
        Optional<TestState> optionalTestState = TestStateExtractor.getOptionalTestStateFrom(new Date());
        assertThat(optionalTestState).isEmpty();
    }

    class StubClassWithNestedTestState extends AnotherStubClass { }

    class AnotherStubClass extends StubClassWithTestState { }

    class StubEmptyTestStateClass {
        private TestState testState;
    }

    class StubClassWithNoTestState {
    }

    class StubClassWithTestState {

        private final TestState someState = new TestState() { //TODO: replace with implementation
            @Override
            public void putOrAddInteresting(String key, Object value) { }

            @Override
            public Map<String, Object> getInterestings() {
                return null;
            }
        };

    }

}