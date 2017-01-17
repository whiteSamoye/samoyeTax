package cn.samoye.nsfw.user.dao;

import java.util.List;

import cn.samoye.core.dao.BaseDao;
import cn.samoye.nsfw.user.bean.User;
import cn.samoye.nsfw.user.bean.UserRole;

public interface UserDao extends BaseDao<User> {
	/**
	 * 根据账号查询用户
	 * @param account
	 */
	User queryUserByAccount(User user);

	boolean verifyAccount(String account);

	List<User> queryUserListByAccountAndId(String account, String id);

	List<User> queryUserByAccount(String account);

	void saveUserRole(UserRole userRole);

	void deleteUserRoleByUser(UserRole userRole);

	void updateUserRole(UserRole userRole);

	List<UserRole> queryUserRoleByUserId(String id);

	void deleteUserRoleByUserId(String id);
}
