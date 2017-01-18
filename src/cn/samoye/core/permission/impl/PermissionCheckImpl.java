package cn.samoye.core.permission.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import cn.samoye.core.permission.PermissionCheck;
import cn.samoye.nsfw.role.bean.RolePrivilege;
import cn.samoye.nsfw.user.bean.User;
import cn.samoye.nsfw.user.bean.UserRole;
import cn.samoye.nsfw.user.service.UserService;

public class PermissionCheckImpl implements PermissionCheck{

	@Resource
	private UserService userService;
	@Override
	public boolean isAccessible(User user, String code) {
		//用户的角色在登录的时候,已经设置到user对象中
		List<UserRole> userRoleList = user.getUserRole();
		
		if(userRoleList == null){
			userRoleList = userService.queryUserRoleByUserId(user.getId());
		}
		
		if(userRoleList != null && userRoleList.size()>0){
			for (UserRole userRole : userRoleList) {
				Set<RolePrivilege> rolePrivileges = userRole.getUserRoleId().getRole().getRolePrivileges();
				for (RolePrivilege rolePrivilege : rolePrivileges) {
					if(code.equals(rolePrivilege.getRolePrivilegeId().getCode())){
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
}
