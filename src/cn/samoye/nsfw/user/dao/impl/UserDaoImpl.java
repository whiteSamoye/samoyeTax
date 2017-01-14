package cn.samoye.nsfw.user.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.samoye.core.dao.impl.BaseDaoImpl;
import cn.samoye.nsfw.user.bean.User;
import cn.samoye.nsfw.user.dao.UserDao;
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User queryUserByAccount(User user) {
		Query query = this.getSession().createQuery("from User where account=?");
		query.setParameter(0, user.getAccount());
		return (User)query.list().get(0);
	}


}
