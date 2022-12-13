package org.example.cache.intf;

import org.example.cache.AbstractCache;

public interface CacheFactory<K,V> {
    Cache<K, V> getCache(int size, EvictionPolicy<K> evictionPolicy);

    AbstractCache.CacheBuilder<K, V> getCacheBuilder(int size, EvictionPolicy<K> evictionPolicy);
}
