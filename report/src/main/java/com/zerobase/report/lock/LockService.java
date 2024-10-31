package com.zerobase.report.lock;

import com.zerobase.report.exception.BasicErrorCode;
import com.zerobase.report.exception.LockException;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LockService {

    private final RedissonClient redisson;

    public void lock(String key) throws LockException {
        RLock lock = redisson.getLock(key);

        boolean isLock = false;
        try {
            isLock = lock.tryLock(20, 10, TimeUnit.SECONDS);
            if (!isLock) {
                throw new LockException(BasicErrorCode.COMMON_SYSTEM_ERROR);
            }
        } catch (InterruptedException e) {
            throw new LockException(BasicErrorCode.COMMON_SYSTEM_ERROR);
        }
    }

    public void unlock(String key) {
        redisson.getLock(key).unlock();
    }


}
