package extension;

import extension.test.state.DefaultTestState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ReportExtension.class)
public class ReportExtensionExampleTest {

    public static final String CHICKEN = "Chicken";
    public static final String RABBIT = "Rabbit";
    public static final String PIG = "Pig";

    public DefaultTestState testState = new DefaultTestState();

    @Test
    void somethingFail() {
        Assertions.fail();
    }

    @Test
    void somethingPass() {
        givenWeOwnA(EMPTY_FARM);

        whenWeDoSomething(likeAdd(CHICKEN));
        andSomethingElse(likeAdd(RABBIT));
        andSomethingElse(likeAdd(PIG));

        assertThat(FARM)
                .contains(CHICKEN)
                .contains(RABBIT)
                .contains(PIG);
    }

    private List<String> EMPTY_FARM = new ArrayList<>();
    private List<String> FARM = new ArrayList<>();

    private void givenWeOwnA(List<String> x) {
        this.FARM = this.EMPTY_FARM;
    }

    private String likeAdd(String x) {
        this.testState.addInteresting("Farm Animals", x);
        this.FARM.add(x);
        return x;
    }

    private void andSomethingElse(String x) {
    }

    private void whenWeDoSomething(String x) {
    }
}