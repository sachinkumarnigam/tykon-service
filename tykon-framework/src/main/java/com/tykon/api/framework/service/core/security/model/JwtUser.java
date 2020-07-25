package com.tykon.api.framework.service.core.security.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tykon.api.framework.service.core.constant.AppConstant;

/**
 * Created by sachin on 20.03.16.
 */
public class JwtUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4305267549447957595L;
	private String id;
	private Long profileTypeId;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean enabled;
	private Date lastPasswordResetDate;
	private String salt = AppConstant.USER_SALT;
	private List<String> roles;
	private String schoolId;
	private String image;

	public JwtUser(String id, String username, String firstname, String lastname, String email, String password,
			Collection<? extends GrantedAuthority> authorities, boolean enabled, Date lastPasswordResetDate,
			Long profileTypeId, List<String> roles, String schoolId, String image) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.profileTypeId = profileTypeId;
		this.roles = roles;
		this.schoolId = schoolId;
		this.image = image;
	}

	public JwtUser() {
	}

	@JsonIgnore
	public String getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@JsonIgnore
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	/**
	 * @return the profileTypeId
	 */
	public Long getProfileTypeId() {
		return profileTypeId;
	}

	/**
	 * @return the salt
	 */
	@JsonIgnore
	public String getSalt() {
		return salt;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public String getImage() {
		return image;
	}

}
