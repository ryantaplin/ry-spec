package extension;

import java.util.Optional;

public interface CapturedInteraction {

    String getInteractionParticipants();
    Optional<Object> getInteractionValue();
}
