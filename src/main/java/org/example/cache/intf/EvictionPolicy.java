package org.example.cache.intf;

import java.util.concurrent.ConcurrentLinkedDeque;

public interface EvictionPolicy<T> {
    T remove(ConcurrentLinkedDeque<T> queue);
}
