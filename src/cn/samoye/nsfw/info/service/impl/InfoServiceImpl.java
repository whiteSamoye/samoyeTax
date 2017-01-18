package cn.samoye.nsfw.info.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.samoye.core.service.impl.BaseServiceImpl;
import cn.samoye.nsfw.info.bean.Info;
import cn.samoye.nsfw.info.dao.InfoDao;
import cn.samoye.nsfw.info.service.InfoService;
/**
 * 通过set方法向父类中baseDao属性设置值
 * @author samoye
 *
 */
@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService{

	
	private InfoDao infoDao;
	
	@Resource
	public void setInfoDao(InfoDao infoDao) {
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}
	

}
