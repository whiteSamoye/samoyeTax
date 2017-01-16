package cn.samoye.nsfw.role.bean;

import java.io.Serializable;

public class RolePrivilege implements Serializable{
	private RolePrivilegeId rolePrivilegeId;

	
	public RolePrivilege() {
		super();
	}

	public RolePrivilege(RolePrivilegeId rolePrivilegeId) {
		super();
		this.rolePrivilegeId = rolePrivilegeId;
	}

	public RolePrivilegeId getRolePrivilegeId() {
		return rolePrivilegeId;
	}

	public void setRolePrivilegeId(RolePrivilegeId rolePrivilegeId) {
		this.rolePrivilegeId = rolePrivilegeId;
	}
	
	
}
