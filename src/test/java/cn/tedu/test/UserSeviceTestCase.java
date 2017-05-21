package cn.tedu.test;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.entity.User;
import cn.tedu.note.service.UserService;

public class UserSeviceTestCase 
	extends BaseTestCase{
	UserService service;
	@Before
	public void initService(){
		service=ctx.getBean(
				"userService", UserService.class);
	}
	@Test
	public void testLogin(){
		String name="zhoujia";
		String password="123";
		User user=service.login(name, password);
		System.out.println(user);
	}
	
	@Test
	public void testRegist(){
		User user = service.regist
			("Jerry", "Mouse", "123", "123");
		System.out.println(user); 
	}
	
	
	
	
	
}





