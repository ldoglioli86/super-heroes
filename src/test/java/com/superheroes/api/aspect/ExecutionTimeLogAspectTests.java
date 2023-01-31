package com.superheroes.api.aspect;

import com.superheroes.aspect.ExecutionTimeLogAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class ExecutionTimeLogAspectTests {

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;
    @Mock
    private Signature signatureMock;

    private ExecutionTimeLogAspect sampleAspect = new ExecutionTimeLogAspect();

    @Test
    public void logExecutionTime() throws Throwable {
        when(proceedingJoinPoint.getSignature()).thenReturn(signatureMock);

        sampleAspect.logExecutionTime(proceedingJoinPoint);

        verify(proceedingJoinPoint, times(1)).proceed();
    }
}
