package org.example.cache.impl;

import org.example.cache.AbstractCache;

public class LRUCache<K,V> extends AbstractCache<K,V> {
    public LRUCache(int size) {
        super(new CacheFactoryImpl<K,V>().getCacheBuilder(size, new LRUEvictionPolicy<>()));
    }
}
