package org.example.cache.impl;

import org.example.cache.intf.EvictionPolicy;

import java.util.concurrent.ConcurrentLinkedDeque;

public class LRUEvictionPolicy<T> implements EvictionPolicy<T> {
    @Override
    public T remove(ConcurrentLinkedDeque<T> queue) {
        return queue.removeFirst();
    }
}
