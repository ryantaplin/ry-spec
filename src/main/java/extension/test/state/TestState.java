package extension.test.state;

import java.util.List;
import java.util.Map.Entry;

public interface TestState {

    //TODO: TestMethodInteractions, TestMethodCaptures
    void addInteresting(String key, Object... value);
    List<Entry<String, List<Object>>> getInterestingEntryList();

}
