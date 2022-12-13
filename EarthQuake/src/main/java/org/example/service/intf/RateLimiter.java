package org.example.service.intf;

public interface RateLimiter {
    boolean isAllowed(String apiKey);
}
