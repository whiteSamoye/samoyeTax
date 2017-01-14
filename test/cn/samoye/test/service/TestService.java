package cn.samoye.test.service;

import java.io.Serializable;

import cn.samoye.test.bean.Person;

public interface TestService {
	void say();
	
	void save(Person person);
	
	Person queryPersonById(Serializable id);
}
