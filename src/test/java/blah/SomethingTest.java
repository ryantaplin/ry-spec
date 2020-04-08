package blah;

import extension.ReportExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ReportExtension.class)
class SomethingTest {
    @Test
    void somethingFail() {
        Assertions.fail();
    }

    public static final String CHICKEN = "";

    @Test
    void somethingPass() {
        doSomething(likeAdd(CHICKEN));
        andSomethingElse();
        assertThat(true).isTrue();
    }

    private String likeAdd(String x) {
        return "";
    }

    private void andSomethingElse() {

    }

    private void doSomething(String x) {

    }
}