/**
 * 
 */
package com.tykon.entity.request.base;

/**
 * @author amleshsinha
 *
 */
public class UserRequestIdentity {
	private final String id;
	private final Long userTypeId;
	private final String firstname;
	private final String lastname;
	private final String email;
	private final boolean enabled;
	private final String schoolId;
	private final String image;
	
	
	public UserRequestIdentity(String id, Long userTypeId,String firstname, String lastname,
			String email, boolean enabled, String schoolId, String image) {
		super();
		this.id = id;
		this.userTypeId = userTypeId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.enabled = enabled;
		this.schoolId = schoolId;
		this.image = image;
	}
	
	public String getId() {
		return id;
	}
	public Long getUserTypeId() {
		return userTypeId;
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
	public boolean isEnabled() {
		return enabled;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public String getImage() {
		return image;
	}

}
