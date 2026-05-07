package com.tsm.atelier.shared.security;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Rate limiter distribuído usando Redis (Fixed Window).
 *
 * <p>Adequado para ambientes multi-instance e clusters.
 */
@Component
@RequiredArgsConstructor
public class RateLimiter {

  private final StringRedisTemplate redisTemplate;

  public boolean tryAcquire(String key, int limit, Duration windowDuration) {
    String fullKey = "ratelimit:" + key;

    // Operação atômica de incremento no Redis
    Long count = redisTemplate.opsForValue().increment(fullKey);

    // Se foi a primeira requisição, define a expiração da chave (TTL)
    if (count != null && count == 1L) {
      redisTemplate.expire(fullKey, windowDuration);
    }

    return count != null && count <= limit;
  }
}
