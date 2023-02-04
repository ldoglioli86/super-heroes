package com.superheroes.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ExecutionTimeLogAspect {

    @Around("@annotation(com.superheroes.aspect.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        long endTime = System.currentTimeMillis();
        var executionTime = endTime - startTime;
        log.info(generateLogMessage(
                point.getSignature().getDeclaringTypeName(),
                point.getSignature().getName(),
                executionTime));
        return result;
    }

    private String generateLogMessage(String className, String signatureName, Long executionTime) {
        var sb = new StringBuilder();
        sb.append("Execution Time for, Class: ")
                .append(className)
                .append(", Method: ").append(signatureName)
                .append(" is: ")
                .append(executionTime)
                .append("ms.");
        return sb.toString();
    }
}
