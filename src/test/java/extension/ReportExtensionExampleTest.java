package extension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ReportExtension.class)
class ReportExtensionExampleTest {

    public static final String CHICKEN = "Chicken";
    public static final String DONKEY = "Donkey";

    @Test
    void somethingFail() {
        Assertions.fail();
    }

    //TODO: remove _
    @Test
    void somethingPass() {
        givenWeOwnA(EMPTY_FARM);

        whenWeDoSomething(likeAdd(CHICKEN));
        andSomethingElse(likeAdd(DONKEY));

        assertThat(FARM)
                .contains(CHICKEN)
                .contains(DONKEY);
    }

    private List<String> EMPTY_FARM = new ArrayList<>();
    private List<String> FARM = new ArrayList<>();

    private void givenWeOwnA(List<String> x) {
        this.FARM = this.EMPTY_FARM;
    }

    private String likeAdd(String x) {
        this.FARM.add(x);
        return x;
    }

    private void andSomethingElse(String x) {
    }

    private void whenWeDoSomething(String x) {
    }
}