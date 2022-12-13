package org.example.cache;

import org.example.cache.intf.Cache;
import org.example.cache.intf.EvictionPolicy;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class AbstractCache<K,V> implements Cache<K,V> {
    private int size;
    private ConcurrentHashMap<K, V> cache;
    private ConcurrentLinkedDeque<K> queue;

    private EvictionPolicy<K> evictionPolicy;

    public AbstractCache(CacheBuilder<K,V> cacheBuilder){
        this.size = cacheBuilder.size;
        this.cache = cacheBuilder.cache;
        this.queue = cacheBuilder.queue;
        this.evictionPolicy = cacheBuilder.evictionPolicy;
    }

    private boolean isQueueFull(){
        return (queue.size()==size);
    }

    private boolean isCacheFull(){
        return (cache.size()==size);
    }

    private boolean isPresetInCache(K key){
        return (cache.containsKey(key));
    }

    private void addInCache(K key, V value){
        if(isCacheFull()){
            cache.remove(evictionPolicy.remove(queue));
        }
        cache.put(key, value);
    }

    private void addInQueue(K key) {
        if (isQueueFull()) {
            evictionPolicy.remove(queue);
        }
        queue.addLast(key);
    }

    @Override
    public V get(K key){
        if(isPresetInCache(key)){
            addInQueue(key);
            return cache.get(key);
        }
        return null;
    }

    @Override
    public void put(K key, V value){
        addInCache(key, value);
        addInQueue(key);
    }

    @Override
    public String toString(){
        String out = "";
        Iterator<Map.Entry<K, V>> iterator = cache.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<K, V> data = iterator.next();
            out = out + " Key = " + data.getKey() + " , Value = " + data.getValue() + "\n";
        }
        return out;
    }

    public static class CacheBuilder<K,V>{
        private final int size;
        private ConcurrentHashMap<K, V> cache;
        private ConcurrentLinkedDeque<K> queue;

        private EvictionPolicy<K> evictionPolicy;

        public CacheBuilder(int size){
            this.size = size;
            cache = new ConcurrentHashMap<>();
            queue = new ConcurrentLinkedDeque<>();
        }

        public CacheBuilder<K,V> setEvictionPolicy(EvictionPolicy<K> evictionPolicy){
            this.evictionPolicy = evictionPolicy;
            return this;
        }

        public Cache<K,V> build(){
            return new AbstractCache<K, V>(this);
        }
    }
}
