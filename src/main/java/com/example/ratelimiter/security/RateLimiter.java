package com.example.ratelimiter.security;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiter {
    private Map<String, TreeSet<Long>> cache;
    private static final int WINDOW_S = 6;
    private static final int LIMIT = 6;

    public RateLimiter() {
        cache = new ConcurrentHashMap<>();
    }//********************8

    public boolean checkLimit(String user) {
        long now = System.currentTimeMillis();
        if(!cache.containsKey(user)) {
            TreeSet<Long> set = new TreeSet<>();
            set.add(now);
            cache.put(user, set);
            return true;
        }
        TreeSet<Long> set = cache.get(user);
        deleteOutOfWindow(set, now);
        if(set.size()<6) {
            cache.get(user).add(now);
            return true;
        }
        return false;
    }

    private void deleteOutOfWindow(TreeSet<Long> set, long now) {
        Iterator<Long> iterator = set.iterator();
        while (iterator.hasNext()) {
            Long t = iterator.next();
            if(t< now - WINDOW_S*1000)
                iterator.remove();
            else
                return;
        }
        return;
    }
}
