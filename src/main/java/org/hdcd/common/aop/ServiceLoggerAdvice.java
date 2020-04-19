package org.hdcd.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceLoggerAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceLoggerAdvice.class);
	
	// org.hdcd.service.BoardService 클래스에 속한 임의의 메소드를 대상으로 한다.
	// 1.Before 어드바이스 : 조인 포인트 전에 실행된다. 예외가 발생하는 경우만 제외하고 항상 실행된다.
	// @Before("execution(* org.hdcd.service.AopService*.*(..))")
	
	// 2.After Returning 어드바이스 : 조인 포인트가 정상적으로 종료한 후에 실행된다. 예외가 발생하면 실행되지 않는다.
	// @AfterReturning("execution(* org.hdcd.service.AopService*.*(..))")
	
	// 3.After Throwing 어드바이스 : 조인 포인트에서 예외가 발생했을 때 실행된다. 예외가 발생하지 않고 정상적으로 종료하면 실행되지 않는다.
	// @AfterThrowing(pointcut = "execution(* org.hdcd.service.AopService*.*(..))", throwing = "e")
	
	// 4.After 어드바이스 : 조인 포인트에서 처리가 완료된 후 실행한다. 예외 발생이나 정상 종료 여부와 상관없이 항상 실행된다.
	// @After("execution(* org.hdcd.service.AopService*.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("startLog");
		logger.info("startLog: " + jp.getSignature());
		logger.info("startLog : " + Arrays.deepToString(jp.getArgs()));
	}
	
}
