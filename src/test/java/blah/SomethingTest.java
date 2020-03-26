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

    @Test
    void somethingPass() {
        assertThat(true).isTrue();
    }
}