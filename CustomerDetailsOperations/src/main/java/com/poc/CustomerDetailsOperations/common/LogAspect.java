package com.poc.CustomerDetailsOperations.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(com.poc.CustomerDetailsOperations.annotations.LogAround) && execution(* * (..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            logger.info("Entry in method "+joinPoint.getSignature().toShortString());
            result = joinPoint.proceed();
            logger.info("Exit from method "+joinPoint.getSignature().toShortString());
        } catch (Exception e) {
            logger.error("Exception in method " + joinPoint.getSignature().toShortString() + ": ", e);
            throw e;
        }
        return result;
    }

}
