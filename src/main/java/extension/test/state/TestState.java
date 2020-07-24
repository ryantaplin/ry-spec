package extension.test.state;

import java.util.List;
import java.util.Map.Entry;

public interface TestState {

    //TODO: TestMethodInteractions, TestMethodCaptures
    void addInterestingGiven(String key, Object... value);
    List<Entry<String, List<Object>>> getInterestingGivenEntries();

    void captureInteraction(String sender, String receiver, Object value);
    List<CapturedInteraction> getCapturedInteractions();
}
