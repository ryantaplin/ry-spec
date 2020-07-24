package extension.helpers;

import extension.test.state.CapturedInteraction;
import extension.test.state.DefaultTestState;
import extension.test.state.TestState;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class TestStateExtractorTest {

    @Test
    void returnsTestStateWhenClassHasAPrivatelyDeclaredFinalDefaultTestState() {
        Optional<TestState> optionalTestState = TestStateExtractor.getOptionalTestStateFrom(new StubClassWithDefaultTestState());
        assertThat(optionalTestState).isNotEmpty();
    }

    @Test
    void returnsTestStateWhenClassHasImplementationOfTestState() {
        Optional<TestState> optionalTestState = TestStateExtractor.getOptionalTestStateFrom(new StubClassWithImplemetedTestState());
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

    class AnotherStubClass extends StubClassWithDefaultTestState { }

    class StubEmptyTestStateClass {
        private TestState testState;
    }

    class StubClassWithNoTestState {
    }

    class StubClassWithDefaultTestState {
        private final TestState someState = new DefaultTestState();
    }

    class StubClassWithImplemetedTestState {
        private final TestState someState = new TestState() {
            @Override
            public void addInterestingGiven(String key, Object... value) {

            }

            @Override
            public List<Map.Entry<String, List<Object>>> getInterestingGivenEntries() {
                return null;
            }

            @Override
            public void captureInteraction(String sender, String receiver, Object value) {

            }

            @Override
            public List<CapturedInteraction> getCapturedInteractions() {
                return null;
            }
        };
    }

}