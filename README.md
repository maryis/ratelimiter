# ratelimiter using Redis

with spring boot, and simple local cache


# improvement
use redis

```
private RateLimitInfo checkLimit(String username, boolean apply) {
        String key = envPrefix + username;
        Jedis jedis = jedisPool.getResource();
        long now = System.currentTimeMillis();
        long beforeInterval = now - intervalMS;

        Transaction transaction = jedis.multi();
        transaction.zremrangeByScore(key, 0, beforeInterval);
        if (apply) {
            transaction.zadd(key, now, UUID.randomUUID().toString());
        }
        transaction.zrangeByScoreWithScores(key, 0, System.currentTimeMillis());
        transaction.expire(key, ttlSeconds);
        List<Object> result = transaction.exec();
        Object allUserTrans = apply ? result.get(2) : result.get(1);
        LinkedHashSet<Tuple> userTransList = (LinkedHashSet<Tuple>) allUserTrans;
        List<Long> timeStamps = userTransList.stream().map(tuple -> (long) tuple.getScore()).collect(Collectors.toList());
        jedis.close();
        return calculateLimit(timeStamps);
 }
 ```
