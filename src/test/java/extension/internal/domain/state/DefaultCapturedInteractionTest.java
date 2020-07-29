package extension.internal.domain.state;

import extension.defaults.DefaultCapturedInteraction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultCapturedInteractionTest {

    @Test
    void returnsInputParticipants() {
        DefaultCapturedInteraction capturedInteraction = new DefaultCapturedInteraction("sender", "receiver", "anyValue");
        assertThat(capturedInteraction.getInteractionParticipants()).isEqualTo("sender to receiver");
    }

    @ParameterizedTest
    @MethodSource("emptyInputExamples")
    void returnsUnspecifiedParticipantsForSender(String input) {
        DefaultCapturedInteraction capturedInteraction = new DefaultCapturedInteraction(input, "receiver", "anyValue");
        assertThat(capturedInteraction.getInteractionParticipants()).isEqualTo("Unspecified Participant to receiver");
    }

    @ParameterizedTest
    @MethodSource("emptyInputExamples")
    void returnsUnspecifiedParticipantsForReceiver(String input) {
        DefaultCapturedInteraction capturedInteraction = new DefaultCapturedInteraction("sender", input, "anyValue");
        assertThat(capturedInteraction.getInteractionParticipants()).isEqualTo("sender to Unspecified Participant");
    }

    @Test
    void returnsOptionalValueForCapturedInteraction() {
        DefaultCapturedInteraction capturedInteraction = new DefaultCapturedInteraction("sender", "receiver", "anyValue");
        assertThat(capturedInteraction.getInteractionValue())
                .isPresent().get()
                .isEqualTo("anyValue");
    }

    @Test
    void returnsEmptyValueForCapturedInteraction() {
        DefaultCapturedInteraction capturedInteraction = new DefaultCapturedInteraction("sender", "receiver", null);
        assertThat(capturedInteraction.getInteractionValue()).isEmpty();
    }

    @Test
    void capturedInteractionIsEqual() {
        assertThat(new DefaultCapturedInteraction("sender", "receiver", "anyValue"))
                .isEqualTo(new DefaultCapturedInteraction("sender", "receiver", "anyValue"));
    }

    @ParameterizedTest
    @MethodSource("inequalityExamples")
    void capturedInteractionIsNotEqual(DefaultCapturedInteraction differentCapturedInteraction) {
        DefaultCapturedInteraction capturedInteraction = new DefaultCapturedInteraction("sender", "receiver", "anyValue");
        assertThat(capturedInteraction).isNotEqualTo(differentCapturedInteraction);
    }

    private static Stream<DefaultCapturedInteraction> inequalityExamples() {
        return Stream.of(
                new DefaultCapturedInteraction("differentSender", "receiver", "anyValue"),
                new DefaultCapturedInteraction("sender", "differentReceiver", "anyValue"),
                new DefaultCapturedInteraction("sender", "receiver", "differentValue")
        );
    }

    private static Stream<String> emptyInputExamples() {
        return Stream.of("", " ", null);
    }
}