package cn.samoye.nsfw.role.dao;

import cn.samoye.core.dao.BaseDao;
import cn.samoye.nsfw.role.bean.Role;

public interface RoleDao extends BaseDao<Role> {
	/**
	 * 根据用户角色id删除用户权限表
	 * @param roleId
	 */
	void deleteRolePrivilegeByRoleId(String roleId);

}
