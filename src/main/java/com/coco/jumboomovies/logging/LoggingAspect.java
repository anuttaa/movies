package com.coco.jumboomovies.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.StandardServletAsyncWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Pointcut("(within(com.coco.jumboomovies.controllers..*)" +
            " || within(com.coco.jumboomovies.services..*))" +
            " && !within(com.coco.jumboomovies.*.Authentication*)" +
            " && !within(com.coco.jumboomovies.controllers.ImagesController)")
    public void generalControllerAndServiceMethods() {
    }

    @Pointcut("within(com.coco.jumboomovies.controllers.AuthenticationController)" +
            " || within(com.coco.jumboomovies.services.AuthenticationService)")
    public void authenticationControllerMethods() {
    }

    @Around("authenticationControllerMethods()")
    public Object aroundAuthenticationControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        Object[] args = joinPoint.getArgs();

        Predicate<Object> predicate = (arg ->
                !(arg instanceof HttpServletRequest) &&
                        !(arg instanceof HttpServletResponse) &&
                        !(arg instanceof StandardServletAsyncWebRequest));

        List<Object> filteredArgs = Arrays.stream(args)
                .filter(predicate)
                .toList();

        String argsJson = mapper.writeValueAsString(filteredArgs);
        logger.info("📚📚  Request : {}  in Class: {} ", argsJson, className);

        Object result = joinPoint.proceed(args);

        logger.info("🧶🧶Execution of method {} in class {} completed", className, methodName);
        if (predicate.test(result)) {
            try {
                logger.debug("🚔🏎🛩 Response: {}", mapper.writeValueAsString(result));
            } catch (Throwable ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return result;
    }

    @Around("generalControllerAndServiceMethods()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();

        logMethodInvocation(methodName, className, args);

        Object result = joinPoint.proceed(args);
        if (result instanceof Optional<?> optionalResult) {
            Object unwrappedResult = optionalResult.orElse(null); // or throw exception if needed.

            return logMethodCompilation(methodName, className, unwrappedResult);
        } else {
            return logMethodCompilation(methodName, className, result);
        }
    }

    private Object logMethodCompilation(String methodName, String className, Object result) throws JsonProcessingException {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("👨🏻‍💻 2️⃣ Method: {} in class: {} completed", methodName, className);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("🎃🛒🧦 3️⃣ Response: {}", mapper.writeValueAsString(result));
            }

            return result;
        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
            return result;
        }
    }

    private void logMethodInvocation(String methodName, String className, Object[] args) {
        if (logger.isInfoEnabled()) {
            logger.info("👨🏻‍💻 1️⃣ Method: {} in class: {}", methodName, className);
        }
        if (logger.isDebugEnabled()) {
            try {
                List<Object> filteredArgs = Arrays.stream(args)
                        .map(arg -> {
                            if (arg instanceof MultipartFile file) {
                                return "MultipartFile: {name=" + file.getOriginalFilename() + ", size=" + file.getSize() + " bytes}";
                            }
                            return arg;
                        })
                        .toList();

                String argsJson = mapper.writeValueAsString(filteredArgs);
                logger.debug("🎅🏻 Method above was called with args: {}", argsJson);
            } catch (JsonProcessingException e) {
                logger.error("Failed to serialize method arguments: {}", Arrays.toString(args));
            }
        }
    }

    @Around("@annotation(LogExecutionTime)")
    public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("⌛ {} Executed in {} ms", joinPoint.getSignature().getName(), endTime - startTime);

        return result;
    }
}
