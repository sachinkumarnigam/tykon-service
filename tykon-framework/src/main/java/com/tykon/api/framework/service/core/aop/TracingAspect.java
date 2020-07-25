package com.tykon.api.framework.service.core.aop;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tykon.api.framework.service.core.annotation.ApiLogRequestResponse;
import com.tykon.api.framework.service.core.service.UserDetailsServiceImpl;
import com.tykon.api.framework.service.core.util.ClientUtil;

@Aspect
@Component
public class TracingAspect {


	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	//@Autowired
	//private UserActivityRepository userActivityRepository;
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("@annotation(com.hp.api.framework.service.core.annotation.ApiLogRequestResponse)")
	public void ApiLogRequestResponse() {
	}


	@Around("ApiLogRequestResponse() && @annotation(apiLogRequestResponse)")
	public Object logAfter(ProceedingJoinPoint joinPoint , ApiLogRequestResponse apiLogRequestResponse) throws Throwable{

		String requestURL=null;
		String serverName=null;
		String clientIP=null;
		String userAgent = null;
		String userEmail = null;
		String userId = null;
		String visitorId = null;
		String cmpId = null;
		String countryName = null;
		String cityName = null;
		String postal = null;
		String state = null;
		Double latitude = null;
		Double longitude = null;
		String language = null;
		String pageUrl = null;
		


		StopWatch sw = new StopWatch();
		sw.start(); 
		Object result = null;
		result = joinPoint.proceed();
		sw.stop();
		try{
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpServletRequest req = servletRequestAttributes.getRequest();
			if(req !=null){
				requestURL=req.getRequestURI();
				serverName=req.getServerName();
				clientIP=ClientUtil.getClientIPAddress(req);
				userAgent=ClientUtil.getUserAgent(req);
				language = req.getLocale().getLanguage();

				InetAddress ipAddress = InetAddress.getByName(clientIP);
				
				if(req.getHeader("VisitorId")!=null){
					visitorId = req.getHeader("VisitorId");
				}
				if(req.getHeader("cmpId")!=null){
					cmpId = req.getHeader("cmpId");
				}
				if(req.getHeader("Referer")!=null){
					pageUrl = req.getHeader("Referer");
				}
				
			}
			logger.info("joint Point "+joinPoint.toString() );
			//UserActivity userActivity = new UserActivity(visitorId, pageUrl, requestURL, userId, new Date(), cmpId, language, latitude, longitude, cityName, countryName, userAgent, clientIP, userEmail, postal, state);
			//aopHelper.saveInMongo(joinPoint, apiLogRequestResponse, sw, result, requestURL,serverName,clientIP,userAgent,userEmail,userId);
			//userActivityRepository.save(userActivity);
		}catch (Exception e) {
			logger.error("Error while tracking request ",e);
		}
		return result;
	}

}