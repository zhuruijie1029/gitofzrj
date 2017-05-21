package cn.tedu.test;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.dao.PersonDao;
import cn.tedu.note.entity.Person;

public class PersonDaoTestCase 
	extends BaseTestCase{

	PersonDao dao;
	
	@Before
	public void initDao(){
		dao = ctx.getBean(
			"personDao",PersonDao.class);
	}
	
	@Test
	public void testAddPerson(){
		Person person = 
			new Person("李洪鹤", 30);
		System.out.println(person);//id=null
		//myBatis在添加对象时候，自动的读取自增
		//类型的ID值，填充到person的id属性
		int n = dao.addPerson(person);//id=3
		System.out.println(person); 
		System.out.println(n); 
	}
}







