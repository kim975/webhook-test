package com.zerobase.report.aop;

import com.zerobase.report.exception.BaseException;
import com.zerobase.report.exception.BasicErrorCode;
import com.zerobase.report.exception.LockException;
import com.zerobase.report.lock.LockService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LockAopAspect {

    private final LockService lockService;

    @Around("@annotation(com.zerobase.report.aop.ReportSeqLock) && args(command,..)")
    public Object pointLockAround(
        ProceedingJoinPoint pjp,
        ReportSeqLockInterface command
    ) throws Throwable {

        // lock 취득 시도
        lockService.lock(makePointLockKey(command.getUserId()));

        try {
            return pjp.proceed();
        } catch (LockException e) {
            throw new BaseException(BasicErrorCode.COMMON_SYSTEM_ERROR);
        } finally {
            // lock 해제
            lockService.unlock(makePointLockKey(command.getUserId()));
        }

    }

    private String makePointLockKey(Long userId) {
        return "REPORT_SEQ_LOCK: " + userId;
    }

}
