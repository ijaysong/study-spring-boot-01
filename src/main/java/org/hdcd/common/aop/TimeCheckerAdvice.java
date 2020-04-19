package org.hdcd.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeCheckerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(TimeCheckerAdvice.class);
	
	// 5.Around 어드바이스 : 조인 포인트 전후에 실행된다.
	// @Around("execution(* org.hdcd.service.AopService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed();
		
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName() + " : " + (endTime - startTime));
		
		return result;
	}
	
	// 6.메서드 정보 취득
	// @Before 애너테이션이 붙은 메서드는 JoinPoint라는 매개변수를 통해 실행 중인 메서드의 정보를 구할 수 있다.
	@Before("execution(* org.hdcd.service.AopService*.*(..))")
	public void log(JoinPoint jp) {
		// 프락시가 입혀지기 전의 원본 대상 객체를 가져온다.
		Object targetObject = jp.getTarget();
		
		logger.info("targetObject = " + targetObject);
		
		// 프락시를 가져온다.
		Object thisObject = jp.getThis();
		
		logger.info("thisObject= " + thisObject);
		
		// 인수를 가져온다.
		Object[] args = jp.getArgs();
		
		logger.info("args.length = " + args.length);
	}
}
