package extension.defaults;

import extension.CapturedInteraction;
import extension.TestState;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultTestState implements TestState {

    private Map<String, List<Object>> interestingMap = new HashMap<>();
    private List<CapturedInteraction> capturedInteractions = new ArrayList<>();

    @Override
    public void addInterestingGiven(String key, Object... values) {
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
    public List<Entry<String, List<Object>>> getInterestingGivenEntries() {
        return new ArrayList<>(interestingMap.entrySet());
    }

    @Override
    public void captureInteraction(String sender, String receiver, Object value) {
        capturedInteractions.add(new DefaultCapturedInteraction(sender, receiver, value));
    }

    @Override
    public List<CapturedInteraction> getCapturedInteractions() {
        return capturedInteractions;
    }
}