package cn.samoye.test.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.samoye.test.bean.Person;
import cn.samoye.test.dao.TestDao;
/**
 * 使用HibernateDaoSupport必须向其中注入SessionFactory.
 * public class TestDaoImpl extends HibernateDaoSupport implements TestDao {
 * 	使用注解:
 * 		//这种方式,只是向TestDaoImpl属性中注入对象
 * 		@Resource
		private SessionFactory sessionFactory;
		//这种方式,在父类中的setxxx(),不为final是可以的,但是HibernateDaoSupport的setSessionFactory()是final的.
		@Resource
		@Override
		public void setSessionFactory(SessionFactory sessionFactory) {
			super.setSessionFactory(sessionFactory);
		}
		
	只能通过配置文件:
		这里的name="sessionFactory",是指HibernateDaoSupport中的SessionFactory属性.
		<bean id="testDao" class="cn.samoye.test.TestDaoImpl">
			<property name="sessionFactory" ref="sessionFactory"></property>
		</bean>
 * @author samoye
 *
 */
public class TestDaoImpl extends HibernateDaoSupport implements TestDao {

	@Override
	public void say() {

	}

	@Override
	public void save(Person person) {
		this.getHibernateTemplate().save(person);
	}

	@Override
	public Person queryPersonById(Serializable id) {
		return this.getHibernateTemplate().get(Person.class, id);
	}

}
