package extension;

import extension.defaults.DefaultTestState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportExtensionExampleTest extends AcceptanceTest {

    public static final String CHICKEN = "Chicken";
    public static final String RABBIT = "Rabbit";
    public static final String PIG = "Pig";

    public DefaultTestState testState = new DefaultTestState();

    @Test
    void somethingFail() {
        Assertions.fail();
    }

    @Test //care about the order of interesting givens?
    void somethingPass() {
        givenWeHaveAFarmer("Fred"); //weird output -> " fred"
        andWeOwnA(EMPTY_FARM);

        whenWeDoSomething(likeAdd(CHICKEN));
        andSomethingElse(likeAdd(RABBIT));
        andSomethingElse(likeAdd(PIG));


        assertThat(FARM)
                .contains(CHICKEN)
                .contains(RABBIT)
                .contains(PIG);
    }

    private void givenWeHaveAFarmer(String name) {
        this.testState.addInterestingGiven("Farmers", name);
    }

    private List<String> EMPTY_FARM = new ArrayList<>();
    private List<String> FARM = new ArrayList<>();

    private void andWeOwnA(List<String> x) {
        this.FARM = this.EMPTY_FARM;
    }

    //TODO: what happens if i push an object of same type on the interestings? (duplicates?)
    private String likeAdd(String x) {
        this.testState.addInterestingGiven("Farm Animals", x);
        this.testState.captureInteraction("Shop", "Farm", x);
        this.FARM.add(x);
        return x;
    }

    private void andSomethingElse(String x) {
    }

    private void whenWeDoSomething(String x) {
    }
}