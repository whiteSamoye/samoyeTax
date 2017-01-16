package cn.samoye.nsfw.role.dao.impl;

import org.hibernate.Query;

import cn.samoye.core.dao.impl.BaseDaoImpl;
import cn.samoye.nsfw.role.bean.Role;
import cn.samoye.nsfw.role.dao.RoleDao;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public void deleteRolePrivilegeByRoleId(String roleId) {
		Query query = this.getSession().createQuery("delete from RolePrivilege where rolePrivilegeId.role.roleId=?");
		query.setParameter(0, roleId);
		query.executeUpdate();
	}


}
