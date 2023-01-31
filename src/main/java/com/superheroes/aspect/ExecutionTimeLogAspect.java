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
        log.info("Execution Time for, Class: "+ point.getSignature().getDeclaringTypeName() +", Method: "+ point.getSignature().getName() + " is: " + (endTime-startTime) +"ms.");
        return result;
    }
}
