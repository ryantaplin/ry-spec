package extension.test.state;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CapturedInteractionTest {

    @Test
    void returnsInputParticipants() {
        CapturedInteraction capturedInteraction = new CapturedInteraction("sender", "receiver", "anyValue");
        assertThat(capturedInteraction.getInteractionParticipants()).isEqualTo("sender to receiver");
    }

    @ParameterizedTest
    @MethodSource("emptyInputExamples")
    void returnsUnspecifiedParticipantsForSender(String input) {
        CapturedInteraction capturedInteraction = new CapturedInteraction(input, "receiver", "anyValue");
        assertThat(capturedInteraction.getInteractionParticipants()).isEqualTo("Unspecified Participant to receiver");
    }

    @ParameterizedTest
    @MethodSource("emptyInputExamples")
    void returnsUnspecifiedParticipantsForReceiver(String input) {
        CapturedInteraction capturedInteraction = new CapturedInteraction("sender", input, "anyValue");
        assertThat(capturedInteraction.getInteractionParticipants()).isEqualTo("sender to Unspecified Participant");
    }

    @Test
    void returnsOptionalValueForCapturedInteraction() {
        CapturedInteraction capturedInteraction = new CapturedInteraction("sender", "receiver", "anyValue");
        assertThat(capturedInteraction.getInteractionValue())
                .isPresent().get()
                .isEqualTo("anyValue");
    }

    @Test
    void returnsEmptyValueForCapturedInteraction() {
        CapturedInteraction capturedInteraction = new CapturedInteraction("sender", "receiver", null);
        assertThat(capturedInteraction.getInteractionValue()).isEmpty();
    }

    @Test
    void capturedInteractionIsEqual() {
        assertThat(new CapturedInteraction("sender", "receiver", "anyValue"))
                .isEqualTo(new CapturedInteraction("sender", "receiver", "anyValue"));
    }

    @ParameterizedTest
    @MethodSource("inequalityExamples")
    void capturedInteractionIsNotEqual(CapturedInteraction differentCapturedInteraction) {
        CapturedInteraction capturedInteraction = new CapturedInteraction("sender", "receiver", "anyValue");
        assertThat(capturedInteraction).isNotEqualTo(differentCapturedInteraction);
    }

    private static Stream<CapturedInteraction> inequalityExamples() {
        return Stream.of(
                new CapturedInteraction("differentSender", "receiver", "anyValue"),
                new CapturedInteraction("sender", "differentReceiver", "anyValue"),
                new CapturedInteraction("sender", "receiver", "differentValue")
        );
    }

    private static Stream<String> emptyInputExamples() {
        return Stream.of("", " ", null);
    }
}