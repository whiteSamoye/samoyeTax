package cn.samoye.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.samoye.core.dao.BaseDao;
/**
 * 与数据库的交互都是通过HibernateDaoSupport处理的.
 * @author samoye
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private final static Log log= LogFactory.getLog(BaseDao.class);
	
	private Class<T> clazz;
	public BaseDaoImpl(){
		try {
			System.out.println("BaseDaoImpl子类实例化");
			Type type = this.getClass().getGenericSuperclass();
			ParameterizedType pt = (ParameterizedType) type;
			clazz = (Class<T>) pt.getActualTypeArguments()[0];
		} catch (Exception e) {
			log.error("初始化失败!", e);
		}

	}

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(Serializable id) {
		this.getHibernateTemplate().delete(this.queryObjectById(id));
	}

	@Override
	public T queryObjectById(Serializable id) {
		
		return this.getHibernateTemplate().get(clazz, id);
	}
	
	@Override
	public List<T> queryObjectList() {
		// TODO 具体通过getSession()获取session还是通过getCurrentSession()获取session,是由HIbernateDaoSupport决定的
		
		return this.getSession().createQuery("from "+clazz.getSimpleName()).list();
	}

}
