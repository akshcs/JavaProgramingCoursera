package org.example.cache.impl;

import org.example.cache.AbstractCache;
import org.example.cache.intf.Cache;
import org.example.cache.intf.CacheFactory;
import org.example.cache.intf.EvictionPolicy;

public class CacheFactoryImpl <K,V> implements CacheFactory<K,V> {
    @Override
    public Cache<K,V> getCache(int size, EvictionPolicy<K> evictionPolicy){
        return new AbstractCache.CacheBuilder(size).setEvictionPolicy(evictionPolicy).build();
    }

    @Override
    public AbstractCache.CacheBuilder<K,V> getCacheBuilder(int size, EvictionPolicy<K> evictionPolicy){
        return new AbstractCache.CacheBuilder(size).setEvictionPolicy(evictionPolicy);
    }
}
