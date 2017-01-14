package cn.samoye.nsfw.user.dao;

import cn.samoye.core.dao.BaseDao;
import cn.samoye.nsfw.user.bean.User;

public interface UserDao extends BaseDao<User> {
	/**
	 * 根据账号查询用户
	 * @param account
	 */
	User queryUserByAccount(User user);
}
