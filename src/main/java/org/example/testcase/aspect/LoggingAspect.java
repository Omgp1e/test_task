package org.example.testcase.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Around("@annotation(org.example.testcase.common.annotation.LogExecution)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        log.info("Запуск: {}", method);
        try {
            Object result = joinPoint.proceed();
            log.info("Завершено: {}", method);
            return result;
        } catch (Exception e) {
            log.error("Ошибка в {}: {}", method, e.getMessage(), e);
            throw e;
        }
    }
}
