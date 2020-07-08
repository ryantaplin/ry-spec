package extension;

import extension.test.TestState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ReportExtension.class)
public class ReportExtensionExampleTest {

    public static final String CHICKEN = "Chicken";
    public static final String DONKEY = "Donkey";

    public TestState testState = new TestState() {
//        List<String> x = new ArrayList<>();
        Map<String, Object> x = new HashMap<>();


        @Override
        public void putOrAddInteresting(String key, Object value) {
            if (this.x.containsKey(key)) {
                Object o = x.get(key);
                if (o instanceof List) {
                    ((List) o).add(value);
                } else {
                    x.put(key, Arrays.asList(o, value));
                }
            }
            this.x.put(key, value);
        }

        @Override
        public Map<String, Object> getInterestings() {
            return x;
        }
    };

    @Test
    void somethingFail() {
        Assertions.fail();
    }

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
        this.testState.putOrAddInteresting("Animals", x);
        this.FARM.add(x);
        return x;
    }

    private void andSomethingElse(String x) {
    }

    private void whenWeDoSomething(String x) {
    }
}