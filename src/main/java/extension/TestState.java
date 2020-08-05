package extension;

import java.util.List;
import java.util.Map.Entry;

public interface TestState {

    void addInterestingGiven(String key, Object... value);

    List<Entry<String, List<Object>>> getInterestingGivenEntries();

    void captureInteraction(String sender, String receiver, Object value);

    List<CapturedInteraction> getCapturedInteractions();
}
