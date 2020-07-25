/**
 *
 */
package com.tykon.api.framework.service.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tykon.api.framework.service.core.annotation.Timed;
import com.tykon.api.framework.service.core.constant.AppConstant;
import com.tykon.api.framework.service.core.util.ClientUtil;
import com.tykon.entity.request.base.BaseRequest;
import com.tykon.entity.response.base.BaseHostDetailsResponse;
import com.tykon.entity.response.base.ResponseStatus;

/**
 * @author sachin
 *
 */
public abstract class AppController implements IController {

  

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected Map<String, String> supportedLanguage = new HashMap<>();

    @PostConstruct
    public void init() {
    	supportedLanguage.put("en", "en");
    	supportedLanguage.put("zh-CN", "zh");
    	supportedLanguage.put("zh", "zh");
    	supportedLanguage.put("ru", "ru");
    }

    protected void updateLanguageInRequest(BaseRequest request, HttpServletRequest httpRequest) {
    	if(this.supportedLanguage.get(httpRequest.getLocale().getLanguage()) == null) {
    		request.setLanguage(AppConstant.DEFAULT_LANGUAGE_ENGLISH);
    	} else {
    		request.setLanguage(this.supportedLanguage.get(httpRequest.getLocale().getLanguage()));
    		//request.setLanguage(AppConstant.DEFAULT_LANGUAGE_ENGLISH);
    	}
    }
	

	

	@Timed
	@RequestMapping(value="/host_details", method= RequestMethod.GET)
	public BaseHostDetailsResponse getHostDetails() {
	  return getHostDetail();
	}
	private BaseHostDetailsResponse getHostDetail() {
		// TODO Auto-generated method stub
		BaseHostDetailsResponse baseHostDetailsResponse = new BaseHostDetailsResponse();
		String ip = ClientUtil.getServerIp();
		baseHostDetailsResponse.setIpAddress(ip);
		baseHostDetailsResponse.setStatus(ResponseStatus.SUCCESS);
		return baseHostDetailsResponse;
	}


}
