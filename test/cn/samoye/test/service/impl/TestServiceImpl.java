package cn.samoye.test.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.samoye.test.bean.Person;
import cn.samoye.test.dao.TestDao;
import cn.samoye.test.service.TestService;
/**
 * @Service("testService") 指定ioc中bean名称为 testService
 * @Service bean的名称为testServiceImpl
 * @author samoye
 *
 */
@Service("testService")
public class TestServiceImpl implements TestService {
	@Resource
	private TestDao testDao;
	@Override
	public void say() {
		System.out.println("say...");
	}

	@Override
	public void save(Person person) {
//		int i = 1/0;
		testDao.save(person);
	}

	@Override
	public Person queryPersonById(Serializable id) {
//		this.save(new Person("samoye11"));
		return testDao.queryPersonById(id);
	}

}
