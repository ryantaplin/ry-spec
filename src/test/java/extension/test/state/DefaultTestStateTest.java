package extension.test.state;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultTestStateTest {

    @Test
    void testStateAddsKeyAndSingleItemToMap() {
        final DefaultTestState state = new DefaultTestState();
        state.addInteresting("single-key", "single-item");
        assertThat(state.getInterestingEntryList()).contains(entry("single-key", List.of("single-item")));
    }

    @Test
    void testStateAddsItemToExistingEntryInMap() {
        final DefaultTestState state = new DefaultTestState();
        state.addInteresting("a-key", "first-item");
        state.addInteresting("a-key", "second-item");
        assertThat(state.getInterestingEntryList()).contains(entry("a-key", List.of("second-item", "first-item")));
    }

    @Test
    void testStateAddsKeyWithMultipleItemsInMap() {
        final DefaultTestState state = new DefaultTestState();
        state.addInteresting("a-key", "first-item", "second-item");
        assertThat(state.getInterestingEntryList()).contains(entry("a-key", List.of("first-item", "second-item")));
    }

    private static Map.Entry<String, List<Object>> entry(String key, List<Object> value) {
        return new Map.Entry<>() {
            @Override
            public String getKey() {
                return key;
            }

            @Override
            public List<Object> getValue() {
                return value;
            }

            @Override
            public List<Object> setValue(List<Object> o) {
                return null;
            }
        };
    }
}