package org.example.service.impl;

import org.example.config.RateLimitConfig;
import org.example.service.intf.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class RateLimiterImpl implements RateLimiter {
    private HashMap<String, LinkedList<Date>> userInfo=  new HashMap<>();

    @Autowired
            private RateLimitConfig config;

    RateLimiterImpl(RateLimitConfig config){
        this.config =  config;
        userInfo=  new HashMap<>();
    }

    @Override
    public boolean isAllowed(String apiKey) {
        if(userInfo.get(apiKey).size() < config.getNumberOfRequests()){
            LinkedList<Date> requests = userInfo.get(apiKey);
            requests.add(new Date());
            return true;
        } else{
            LinkedList<Date> requests = userInfo.get(apiKey);
            Date lastDate = requests.getLast();
            // Compare last Date in DD MM YY HH MM SS with current >
            // Diff is greater than
            int mins = config.getMinsForThrottle();
            int diff = new Date().compareTo(lastDate);
            if(diff < mins){
                return false;
            } else {
                requests.removeLast();
                requests.add(new Date());
                return true;
            }
            // req
        }
    }
}
