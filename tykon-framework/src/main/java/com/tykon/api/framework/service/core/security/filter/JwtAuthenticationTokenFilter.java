package com.tykon.api.framework.service.core.security.filter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tykon.api.framework.service.core.security.JwtTokenUtil;
import com.tykon.api.framework.service.core.service.UserDetailsServiceImpl;
import com.tykon.api.framework.service.core.util.NumberUtil;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private RedissonClient redissonClient;

	@Value("${cache.redis.jwt.user.all.ttl}")
	private long allHPJwtUserTTL;

	@Value("${cache.redis.jwt.user.all.key}")
	private String allHPJwtUserKey;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String authToken = request.getHeader(this.tokenHeader);
		// authToken.startsWith("Bearer ")
		// String authToken = header.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(authToken);
		// String userId = jwtTokenUtil.getUserIdFromToken(authToken);

		/*
		 * if (userId != null && !userId.isEmpty()) { boolean userLoginStatus =
		 * userDetailsService.getUserStatus(userId); if (!userLoginStatus) { throw new
		 * ServletException("You are now deactived."); } }
		 */

		// logger.info("checking authentication for user " + username);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// It is not compelling necessary to load the use details from the database. You
			// could also store the information
			// in the token and read it from it. It's up to you ;)
			/*
			 * UserDetails userDetails = getUserFromCache(username); if(userDetails == null)
			 * { userDetails = this.userDetailsService.loadUserByUsername(username);
			 * if(userDetails!=null) { saveUserIntoCache(username, userDetails); } }
			 */

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if (userDetails.isEnabled() == false) {
				throw new ServletException("You are now deactived.");
			}
			// For simple validation it is completely sufficient to just check the token
			// integrity. You don't have to call
			// the database compellingly. Again it's up to you ;)
			if (jwtTokenUtil.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		if (username != null) {
			MDC.put("userEmail", username);
		} else {
			MDC.put("userEmail", "NA " + NumberUtil.genrateRandomNumber());
		}
		chain.doFilter(request, response);
	}

	private UserDetails getUserFromCache(String userName) {
		RMapCache<String, UserDetails> rMap = redissonClient.getMapCache(allHPJwtUserKey);
		if (rMap != null && !rMap.isEmpty()) {
			UserDetails user = rMap.get(userName);
			return user;
		}
		return null;
	}

	private void saveUserIntoCache(String username, UserDetails user) {
		RMapCache<String, UserDetails> rMap = redissonClient.getMapCache(allHPJwtUserKey);
		rMap.put(username, user, allHPJwtUserTTL, TimeUnit.HOURS);
	}

}