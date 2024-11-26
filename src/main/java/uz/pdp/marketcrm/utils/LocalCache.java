package uz.pdp.marketcrm.utils;

import java.util.concurrent.ConcurrentHashMap;


public class LocalCache<K, V> {
    private final ConcurrentHashMap<K, V> map;

    public LocalCache() {
        this.map = new ConcurrentHashMap<>();
    }

    public void put(K key, V value) {
        if (key == null || (key instanceof String val && val.isBlank())) {
            throw new NullPointerException("key is null or empty");
        }

        map.put(key, value);
    }

    public V get(K key) {
        if (key == null || (key instanceof String val && val.isBlank())) {
            throw new NullPointerException("key is null or empty");
        }
        return map.get(key);
    }
}
