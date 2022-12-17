package org.example.cache.intf;

public interface RateLimiter {
    boolean isAllowed(String apiKey);
}
