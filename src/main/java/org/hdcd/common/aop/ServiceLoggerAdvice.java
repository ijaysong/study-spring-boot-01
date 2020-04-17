package org.hdcd.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceLoggerAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceLoggerAdvice.class);
	
	// org.hdcd.service.BoardService 클래스에 속한 임의의 메소드를 대상으로 한다.
	@Before("execution(* org.hdcd.service.AopService*.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("startLog");
		logger.info("startLog: " + jp.getSignature());
		logger.info("startLog : " + Arrays.deepToString(jp.getArgs()));
	}
	
}
