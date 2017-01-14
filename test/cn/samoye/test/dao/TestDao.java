package cn.samoye.test.dao;

import java.io.Serializable;

import cn.samoye.test.bean.Person;

public interface TestDao {
	void say();
	
	void save(Person person);
	
	Person queryPersonById(Serializable id);
}
