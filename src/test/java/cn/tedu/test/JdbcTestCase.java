package cn.tedu.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试数据库连接 
 */
public class JdbcTestCase 
	extends BaseTestCase{

	@Test
	public void testDataSource() 
			throws Exception {
		DataSource ds = ctx.getBean(
			"dataSource", DataSource.class);
		Connection conn = ds.getConnection();
		DatabaseMetaData md=conn.getMetaData();
		System.out.println(md);
		//查询数据库版本
		String n = md.getDatabaseProductName();
		String v = md.getDatabaseProductVersion();
		System.out.println(n+v); 
		conn.close();
	}
}




