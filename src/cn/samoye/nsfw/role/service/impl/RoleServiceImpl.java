package cn.samoye.nsfw.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.samoye.core.exception.ServiceException;
import cn.samoye.nsfw.role.bean.Role;
import cn.samoye.nsfw.role.dao.RoleDao;
import cn.samoye.nsfw.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Resource
	private RoleDao roleDao;
	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public void update(Role role) {
//		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		roleDao.update(role);
	}

	@Override
	public void delete(Serializable id) {
		roleDao.delete(id);
	}

	@Override
	public Role queryRoleById(Serializable id) {
		return roleDao.queryObjectById(id);
	}

	@Override
	public List<Role> queryRoleList() throws ServiceException {
		return roleDao.queryObjectList();
	}

}
