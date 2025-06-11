package ee.taltech.iti0202.generics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class Hash2Map<T, K, V> {

    // Wrong number of type arguments: 3; required: 2
    private final Map<T, Map<K, V>> map = new HashMap<>();

    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public void put(T x, K y, V z) {
        map.putIfAbsent(x, new HashMap<>());
        map.get(x).put(y, z);
    }

    /**
     *
     * @return
     */
    public Set<T> getKeys() {
        return map.keySet();
    }

    /**
     *
     * @param key
     * @return
     */
    public Set<K> getKeys(T key) {
        return map.getOrDefault(key, Collections.emptyMap()).keySet();
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public V get(T x, K y) {
        return map.getOrDefault(x, Collections.emptyMap()).getOrDefault(y, null);
    }

    /**
     *
     * @param x
     * @return
     */
    public Map<K, V> getMap(T x) {
        return map.getOrDefault(x, Collections.emptyMap());
    }

    /**
     *
     * @return
     */
    public List<V> getAllValues() {
        return map.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap(item -> item.values().stream())
                .toList();
    }
}
