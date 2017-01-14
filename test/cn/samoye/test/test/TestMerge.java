package cn.samoye.test.test;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.samoye.test.bean.Person;
import cn.samoye.test.service.TestService;

public class TestMerge {
	private ClassPathXmlApplicationContext cp;
	@Before
	public void testSpringIocLoad()throws Exception{
		 cp = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testSf() throws Exception {
		SessionFactory sf = (SessionFactory) cp.getBean("sessionFactory");
		//TODO openSession()与getCurrentSesion()的区别:
		sf.openSession();
		
//		sf.getCurrentSession();
				
	}
	
	@Test
	public void testDao() throws Exception {
		TestService testService = cp.getBean(TestService.class);
		testService.save(new Person("samoye"));
	}
	
	@Test
	public void testTransactionReadOnly() throws Exception {
		TestService testService = cp.getBean(TestService.class);
		Person person = testService.queryPersonById("ff808181598c1bcd01598c1bcebc0000");
		System.out.println(person.getName());
		
	}
}
