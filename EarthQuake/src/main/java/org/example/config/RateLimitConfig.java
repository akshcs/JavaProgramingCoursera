package org.example.config;

import org.springframework.stereotype.Component;

@Component
public class RateLimitConfig {
    int numberOfRequests;

    public int getMinsForThrottle() {
        return minsForThrottle;
    }

    public void setMinsForThrottle(int minsForThrottle) {
        this.minsForThrottle = minsForThrottle;
    }

    int minsForThrottle;

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public void setNumberOfRequests(int numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }
}
