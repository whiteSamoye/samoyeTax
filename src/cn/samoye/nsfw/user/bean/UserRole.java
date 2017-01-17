package cn.samoye.nsfw.user.bean;

import java.io.Serializable;

public class UserRole implements Serializable{
	private UserRoleId userRoleId;
	
	public UserRole(UserRoleId userRoleId) {
		super();
		this.userRoleId = userRoleId;
	}

	public UserRole() {
		super();
	}

	public UserRoleId getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(UserRoleId userRoleId) {
		this.userRoleId = userRoleId;
	}
	
}
