package cn.samoye.nsfw.user.dao.impl;

import java.util.List;

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

	@Override
	public boolean verifyAccount(String account) {
		
		return false;
	}

	@Override
	public List<User> queryUserListByAccountAndId(String account, String id) {
		Query query = this.getSession().createQuery("from User where account=? and id!=?");
		List list = query.setParameter(0, account).setParameter(1, id).list();
		return list;
	}

	@Override
	public List<User> queryUserByAccount(String account) {
		Query query = this.getSession().createQuery("from User where account=?");
		List list = query.setParameter(0, account).list();
		return list;
	}


}
