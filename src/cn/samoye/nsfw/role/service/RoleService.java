package cn.samoye.nsfw.role.service;

import java.io.Serializable;
import java.util.List;

import cn.samoye.core.exception.ServiceException;
import cn.samoye.nsfw.role.bean.Role;

public interface RoleService {
	void save(Role role);
	void update(Role role);
	void delete(Serializable id);
	Role queryRoleById(Serializable id);
	List<Role> queryRoleList()throws ServiceException;
}
