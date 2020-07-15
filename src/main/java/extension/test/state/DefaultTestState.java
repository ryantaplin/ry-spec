package extension.test.state;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultTestState implements TestState {

    private Map<String, List<Object>> interestingMap = new HashMap<>();

    @Override
    public void addInteresting(String key, Object... values) {
        if (interestingMap.containsKey(key)) {
            List<Object> existingEntry = interestingMap.get(key);

            interestingMap.put(key, Stream.concat(
                    Arrays.stream(values),
                    existingEntry.stream()
            ).collect(Collectors.toList()));
        } else {
            this.interestingMap.put(key, Arrays.asList(values));
        }
    }

    @Override
    public List<Entry<String, List<Object>>> getInterestingEntryList() {
        return new ArrayList<>(interestingMap.entrySet());
    }
}