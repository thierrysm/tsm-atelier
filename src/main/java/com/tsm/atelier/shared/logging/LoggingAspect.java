package com.tsm.atelier.shared.logging;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  private static final int MAX_ARG_LENGTH = 100;

  @Pointcut(
      "within(@org.springframework.web.bind.annotation.RestController *) || "
          + "within(@org.springframework.stereotype.Service *)")
  public void applicationPackagePointcut() {}

  @Around("applicationPackagePointcut()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
    String args = summarizeArgs(joinPoint.getArgs());

    log.info("==> [EXEC] Entering {}.{}() with args: {}", className, methodName, args);

    long start = System.currentTimeMillis();
    try {
      Object result = joinPoint.proceed();
      long executionTime = System.currentTimeMillis() - start;
      log.info("<== [DONE] {}.{}() executed in {}ms", className, methodName, executionTime);
      return result;
    } catch (Throwable e) {
      log.error("[FAIL] {}.{}() threw exception: {}", className, methodName, e.getMessage());
      throw e;
    }
  }

  @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "e")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    log.error(
        "Exception in {}.{}() with cause = {}",
        joinPoint.getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName(),
        e.getCause() != null ? e.getCause() : "NULL");
  }

  /**
   * Resolve o problema de OVER-LOGGING: 1. Trunca strings muito longas (evita logar payloads
   * gigantes). 2. Identifica coleções e loga apenas o tamanho (evita logar milhares de itens). 3.
   * Filtra objetos complexos/binários que não fazem sentido em texto.
   */
  private String summarizeArgs(Object[] args) {
    if (args == null || args.length == 0) {
      return "[]";
    }

    return Arrays.stream(args)
        .map(
            arg -> {
              if (arg == null) return "null";

              if (arg instanceof Collection<?> c) {
                return "[Collection size: " + c.size() + "]";
              }

              String str = arg.toString();
              if (str.length() > MAX_ARG_LENGTH) {
                return str.substring(0, MAX_ARG_LENGTH) + "...(truncated)";
              }
              return str;
            })
        .collect(Collectors.joining(", ", "[", "]"));
  }
}
