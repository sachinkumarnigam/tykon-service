/**
 * 
 */
package com.tykon.api.framework.service.core.aop;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author sachin
 *
 */
@Aspect
@Component
public class LoggingAspect {
	
	 protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("@annotation(com.hp.api.framework.service.core.annotation.Timed)")
    public void timed() {}
	
	@Pointcut("within(com.hp..*)")
    public void inHelloParent() {}
	
	@Around("timed() && inHelloParent()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch();
        String name = pjp.getSignature().getName();
        try {
            sw.start();
            return pjp.proceed();
        } finally {
            sw.stop();
            logger.info("response time of method : "+name +" - " + sw.getTime()+" ms");
        }
    }
}
