package extension.helpers;

import extension.test.resources.StubClass;
import extension.test.resources.StubClassWithTestState;
import extension.test.TestState;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class TestStateExtractorTest {

    @Test
    void returnsEmptyWhenClassDoesNotHaveTestState() {
        Optional<TestState> optionalTestState = TestStateExtractor.getOptionalTestStateFrom(new StubClass());
        assertThat(optionalTestState).isEmpty();
    }

    @Test
    void returnsTestStateWhenClassHasAPrivatelyDeclaredFinalTestState() {
        Optional<TestState> optionalTestState = TestStateExtractor.getOptionalTestStateFrom(new StubClassWithTestState());
        assertThat(optionalTestState).isNotEmpty();
    }

    @Test
    void bl() {
        Optional<TestState> optionalTestState = TestStateExtractor.getOptionalTestStateFrom(new Date());
        assertThat(optionalTestState).isEmpty();
    }

    //TODO: recursive search in class hierarchy.
}