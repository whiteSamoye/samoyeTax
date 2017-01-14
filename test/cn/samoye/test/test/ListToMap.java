package cn.samoye.test.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.samoye.test.bean.Person;

public class ListToMap {
	@Test
	public void test()throws Exception{
		List<Person> perList = new ArrayList<Person>();
		perList.add(new Person("1","samoye"));
		perList.add(new Person("2","samoye2"));
		
		String str1 = JSON.toJSONString(perList);
		System.out.println(str1);
		List<Map<String, String>> listMap = JSON.parseObject(str1, new TypeReference<List<Map<String,String>>>(){});
		System.out.println(listMap); 
	}
	
	@Test
	public void testName() throws Exception {
//		Integer i = null;
//		int j = i;
//		System.out.println(j);
	}
}
