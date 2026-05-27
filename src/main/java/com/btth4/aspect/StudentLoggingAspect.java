package com.btth4.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class StudentLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(StudentLoggingAspect.class);

    // 1. @Before: Log tên method + danh sách tham số đầu vào khi truy cập Controller
    @Before("execution(* com.btth4.controller.StudentController.*(..))")
    public void logBeforeController(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("[AOP @Before] Chặn trước Method: {} | Tham số: {}", methodName, args);
    }

    // 2. @AfterThrowing: Chạy ngay lập tức khi Service ném lỗi ra ngoài
    @AfterThrowing(
            pointcut = "execution(* com.btth4.service.StudentService.*(..))",
            throwing = "exception"
    )
    public void logAfterServiceThrowing(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("[AOP @AfterThrowing] CẢNH BÁO! Method lỗi: {} | Message lỗi: {}", methodName, exception.getMessage());
    }

    // 3. @Around: Đo đạc thời gian chạy (ms) của các API trong Controller
    @Around("execution(* com.btth4.controller.StudentController.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();

        Object result = joinPoint.proceed(); // Thực thi method gốc

        long executionTime = System.currentTimeMillis() - start;
        logger.info("[AOP @Around] Kết thúc Method: {} | Thời gian xử lý: {} ms", methodName, executionTime);

        return result;
    }
}
