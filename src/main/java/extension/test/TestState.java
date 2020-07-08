package extension.test;

import java.util.List;
import java.util.Map;

public interface TestState {

    void putOrAddInteresting(String key, Object value);
    Map<String, Object> getInterestings();

}
