package com.sergeyvolkodav.tokenbucket;

import java.time.Instant;

public class RateLimiting {

    private int maxTokens;
    private long lastRequestTime = System.currentTimeMillis();
    private long possibleTokens = 0;
    private int delay;

    public RateLimiting(int maxTokens) {
        // 1 token per second is a minimum
        this.maxTokens = Math.max(maxTokens, 1);
        this.delay = 1000 / maxTokens;
    }

    public synchronized void getToken() throws InterruptedException {

        possibleTokens += (System.currentTimeMillis() - lastRequestTime) / delay;

        if (possibleTokens > maxTokens) {
            possibleTokens = maxTokens;
        }

        if (possibleTokens == 0) {
            Thread.sleep(delay);
        } else {
            possibleTokens--;
        }
        lastRequestTime = System.currentTimeMillis();

        System.out.println("Granting " + Thread.currentThread().getName() + " token at " + (Instant.now()));

    }
}
