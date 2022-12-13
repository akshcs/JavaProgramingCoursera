package org.example.cache.impl;

import org.example.cache.AbstractCache;

public class MRUCache<K,V> extends AbstractCache<K,V> {
    public MRUCache(int size) {
        super(new AbstractCache.CacheBuilder(size).setEvictionPolicy(new MRUEvictionPolicy()));
    }
}
