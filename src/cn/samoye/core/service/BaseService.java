package cn.samoye.core.service;

import java.io.Serializable;
import java.util.List;

import cn.samoye.nsfw.info.bean.Info;

public interface BaseService<T> {
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
