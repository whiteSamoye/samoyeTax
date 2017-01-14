package cn.samoye.core.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	/**
	 * 保存
	 * @param t
	 */
	void save(T t);
	/**
	 * 更新
	 * @param t
	 */
	void update(T t);
	/**
	 * 删除
	 * @param id
	 */
	void delete(Serializable id);
	/**
	 * 根据id查询bean
	 * @param id
	 * @return
	 */
	T queryObjectById(Serializable id);
	/**
	 * 查询列表集合
	 * @return
	 */
	List<T> queryObjectList();
}
