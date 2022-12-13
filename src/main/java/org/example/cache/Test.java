package org.example.cache;

import org.example.cache.impl.*;
import org.example.cache.intf.Cache;
import org.example.cache.intf.CacheFactory;

public class Test {
    @org.junit.jupiter.api.Test
    public void testLRUCache(){
        System.out.println("LRU Cache");
        Cache<Integer, Integer> cache =  new LRUCache<>(5);
        cache.put(1,7);
        cache.put(2,8);
        cache.put(3,9);
        cache.put(4,10);
        cache.put(5,11);
        cache.get(1);
        cache.put(6,12);
        System.out.println(cache);
    }

    @org.junit.jupiter.api.Test
    public void testMRUCache(){
        System.out.println("MRU Cache");
        Cache<Integer, Integer> cache =  new MRUCache<>(5);
        cache.put(1,7);
        cache.put(2,8);
        cache.put(3,9);
        cache.put(4,10);
        cache.put(5,11);
        cache.get(1);
        cache.put(6,12);
        System.out.println(cache);
    }

    @org.junit.jupiter.api.Test
    public void testLRUCacheFromBuilder(){
        System.out.println("LRU Cache with Builder");
        Cache<Integer, Integer> cache =  new AbstractCache.CacheBuilder<Integer, Integer>(5).setEvictionPolicy(new LRUEvictionPolicy<>()).build();
        cache.put(1,7);
        cache.put(2,8);
        cache.put(3,9);
        cache.put(4,10);
        cache.put(5,11);
        cache.get(1);
        cache.put(6,12);
        System.out.println(cache);
    }

    @org.junit.jupiter.api.Test
    public void testMRUCacheFromBuilder(){
        System.out.println("MRU Cache with Builder");
        Cache<Integer, Integer> cache =  new AbstractCache.CacheBuilder<Integer, Integer>(5).setEvictionPolicy(new MRUEvictionPolicy<>()).build();
        cache.put(1,7);
        cache.put(2,8);
        cache.put(3,9);
        cache.put(4,10);
        cache.put(5,11);
        cache.get(1);
        cache.put(6,12);
        System.out.println(cache);
    }

    @org.junit.jupiter.api.Test
    public void testLRUCacheFromFactory(){
        System.out.println("LRU Cache from Factory");
        CacheFactory<Integer, Integer> cacheFactory =  new CacheFactoryImpl<>();
        Cache<Integer, Integer> cache = cacheFactory.getCache(5, new LRUEvictionPolicy());
        cache.put(1,7);
        cache.put(2,8);
        cache.put(3,9);
        cache.put(4,10);
        cache.put(5,11);
        cache.get(1);
        cache.put(6,12);
        System.out.println(cache);
    }

    @org.junit.jupiter.api.Test
    public void testMRUCacheFromFactory(){
        System.out.println("MRU Cache From Factory");
        CacheFactory<Integer, Integer> cacheFactory =  new CacheFactoryImpl<>();
        Cache<Integer, Integer> cache = cacheFactory.getCache(5, new MRUEvictionPolicy());
        cache.put(1,7);
        cache.put(2,8);
        cache.put(3,9);
        cache.put(4,10);
        cache.put(5,11);
        cache.get(1);
        cache.put(6,12);
        System.out.println(cache);
    }
}
