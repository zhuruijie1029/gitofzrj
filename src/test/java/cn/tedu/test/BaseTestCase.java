package cn.tedu.test;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTestCase {
	protected ClassPathXmlApplicationContext ctx;
	//初始化 Spring容器
	@Before 
	public void init(){
		ctx=new ClassPathXmlApplicationContext(
				"conf/spring-web.xml",
				"conf/spring-mybatis.xml",				
				"conf/spring-aop.xml",
				"conf/spring-service.xml");
	}
}




