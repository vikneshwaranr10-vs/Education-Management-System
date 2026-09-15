package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.example.serviceimpl..*(..))")
    public void logBefore(JoinPoint joinPoint) {

        log.info("Method started: {}",
                joinPoint.getSignature().toShortString());
    }

    @AfterReturning("execution(* com.example.serviceimpl..*(..))")
    public void logAfter(JoinPoint joinPoint) {

        log.info("Method completed: {}",
                joinPoint.getSignature().toShortString());
    }
}