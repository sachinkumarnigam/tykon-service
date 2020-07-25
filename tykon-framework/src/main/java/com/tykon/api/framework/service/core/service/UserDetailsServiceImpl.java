package com.tykon.api.framework.service.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tykon.api.framework.connector.rest.base.RestClient;

/**
 * Created by sachin
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RestClient restClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}