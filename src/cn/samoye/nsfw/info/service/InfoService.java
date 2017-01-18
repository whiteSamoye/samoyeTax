package cn.samoye.nsfw.info.service;

import java.io.Serializable;
import java.util.List;

import cn.samoye.nsfw.info.bean.Info;

public interface InfoService {
	/**
	 * 保存
	 * @param t
	 */
	void save(Info info);
	/**
	 * 更新
	 * @param t
	 */
	void update(Info info);
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
	Info queryInfoById(Serializable id);
	/**
	 * 查询列表集合
	 * @return
	 */
	List<Info> queryInfoList();
}
