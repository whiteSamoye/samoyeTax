package cn.samoye.nsfw.info.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.samoye.nsfw.info.bean.Info;
import cn.samoye.nsfw.info.dao.InfoDao;
import cn.samoye.nsfw.info.service.InfoService;
@Service("infoService")
public class InfoServiceImpl implements InfoService{

	@Resource
	private InfoDao infoDao;
	@Override
	public void save(Info info) {
		infoDao.save(info);
	}

	@Override
	public void update(Info info) {
		infoDao.update(info);
	}

	@Override
	public void delete(Serializable id) {
		infoDao.delete(id);
	}

	@Override
	public Info queryInfoById(Serializable id) {
		return infoDao.queryObjectById(id);
	}

	@Override
	public List<Info> queryInfoList() {
		return infoDao.queryObjectList();
	}

}
