package cn.samoye.core.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.samoye.core.dao.BaseDao;
import cn.samoye.core.service.BaseService;
/**
 * baseDao对象的来源:在BaseServiceImpl子类通过set方法向ioc注入对象的时候,调用其父类的set方法,设置baseDao对象.
 * @author samoye
 *
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T>{
	private BaseDao<T> baseDao;
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public void save(T t) {
		baseDao.save(t);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	@Override
	public T queryObjectById(Serializable id) {
		return baseDao.queryObjectById(id);
	}

	@Override
	public List<T> queryObjectList() {
		return baseDao.queryObjectList();
	}

}
