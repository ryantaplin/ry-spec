package extension.test.state;

import java.util.Objects;
import java.util.Optional;

public class CapturedInteraction {

    private String sender;
    private String receiver;
    private Object value;

    public CapturedInteraction(String sender, String receiver, Object value) {
        this.sender = sender;
        this.receiver = receiver;
        this.value = value;
    }

    public String getInteractionParticipants() {
        return String.format("%s to %s", parseParticipant(sender), parseParticipant(receiver));
    }

    private String parseParticipant(String participant) {
        return Optional.ofNullable(participant)
                .filter(input -> !input.isBlank())
                .orElse("Unspecified Participant");
    }

    public Optional<Object> getInteractionValue() {
        return Optional.ofNullable(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapturedInteraction that = (CapturedInteraction) o;
        return Objects.equals(sender, that.sender) &&
                Objects.equals(receiver, that.receiver) &&
                Objects.equals(value, that.value);
    }
}