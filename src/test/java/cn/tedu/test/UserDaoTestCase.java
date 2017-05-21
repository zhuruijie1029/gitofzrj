package cn.tedu.test;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;

public class UserDaoTestCase 
	extends BaseTestCase{
	UserDao dao;
	
	@Before
	public void initDao(){
		dao=ctx.getBean(
				"userDao", UserDao.class);
	}
	
	@Test 
	public void testFindUserByName(){
		String name="zhoujia";
		User user=dao.findUserByName(name);
		System.out.println(user);
	}
	
	@Test
	public void testSaveUser(){
		User user = new User("007",
				"Tom", "123", "", "Cat");
		int i = dao.saveUser(user);
		System.out.println(i); 
	}
}






