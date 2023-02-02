package com.superheroes.api.aspect;

import com.superheroes.aspect.ExecutionTimeLogAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
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
