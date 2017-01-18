package cn.samoye.nsfw.user.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.samoye.core.dao.impl.BaseDaoImpl;
import cn.samoye.nsfw.user.bean.User;
import cn.samoye.nsfw.user.bean.UserRole;
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

	@Override
	public void saveUserRole(UserRole userRole) {
		this.getHibernateTemplate().save(userRole);
	}

	@Override
	public void deleteUserRoleByUser(UserRole userRole) {
		this.getHibernateTemplate().delete(userRole);
	}

	@Override
	public void updateUserRole(UserRole userRole) {
		this.getHibernateTemplate().update(userRole);
	}

	@Override
	public List<UserRole> queryUserRoleByUserId(String id) {
		Query query = this.getSession().createQuery("from UserRole where userRoleId.userId=?");
		query.setParameter(0, id);
		return query.list();
	}

	@Override
	public void deleteUserRoleByUserId(String id) {
		Query query = this.getSession().createQuery("delete UserRole where userRoleId.userId=?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<User> queryUserByAccountAndPassword(String account, String password) {
		Query query = this.getSession().createQuery("from User where account=? and password=?");
		query.setParameter(0, account);
		query.setParameter(1, password);
		return query.list();
	}


}
