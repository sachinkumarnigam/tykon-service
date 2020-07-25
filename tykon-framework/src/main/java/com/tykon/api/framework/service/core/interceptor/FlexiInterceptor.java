/**
 * 
 */
package com.tykon.api.framework.service.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sachin
 *
 */
public class FlexiInterceptor implements HandlerInterceptor {

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.
   * HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object,
   * java.lang.Exception)
   */
  @Override
  public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
      Exception arg3) throws Exception {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest
   * , javax.servlet.http.HttpServletResponse, java.lang.Object,
   * org.springframework.web.servlet.ModelAndView)
   */
  @Override
  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
      ModelAndView arg3) throws Exception {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest
   * , javax.servlet.http.HttpServletResponse, java.lang.Object)
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    // TODO Auto-generated method stub
    return false;
  }

}
