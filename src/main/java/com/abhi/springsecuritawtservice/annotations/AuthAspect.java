package com.abhi.springsecuritawtservice.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.abhi.springsecuritawtservice.service.RateLimitingService;

/**
 * 
 * @author abhimanyu
 *
 *         Aspect handling the conditional method execution. accompanied by
 *         annotation @ConditionalMethodExecution
 */

@Aspect
@Component
@EnableAspectJAutoProxy
public class AuthAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthAspect.class);

	@Autowired
	private RateLimitingService rateLimitingService;

	// Define PointCuts
	@Pointcut("within(@com.abhi.springsecuritawtservice.annotations.RateLimit *)")
	public void beanAnnotatedWithRateLimit() {
	}

	@Pointcut("@annotation(RateLimit)")
	public void methodAnnotatedWithRateLimit() {
	}

	@Pointcut("beanAnnotatedWithRateLimit() || methodAnnotatedWithRateLimit()")
	public void methodOrClassAnnotatedWithRateLimit() {
	}

	@Around("methodOrClassAnnotatedWithRateLimit()")
	public void checkIfThrottle(ProceedingJoinPoint joinPoint)
			throws InstantiationException, IllegalAccessException, Throwable {


		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		LOGGER.info("------------------ Checking Throtting limit for user = {} ",username);
		boolean allowed = rateLimitingService.limit(username, 3);
		LOGGER.info("------------------  user = {}, allowed = {} ",username,allowed);
		if (allowed) {
			joinPoint.proceed();
			LOGGER.error("---------- Continue to the API ---------------");
		} else {
			throw new RuntimeException("user " + username + " has exhausetd all its requests for current window");
		}
	}
}
