package cn.tedu.test;

import org.junit.Test;

import org.apache.commons.codec.digest.DigestUtils;

public class UtilTestCase {

	@Test
	public void testMD5(){
		String str = "123";
		String salt = "您好中国";
		//计算123的摘要
		String md5=DigestUtils.md5Hex(str);
		//计算123加盐的摘要
		String md=DigestUtils.md5Hex(str+salt);
		System.out.println(md5);
		System.out.println(md);
	}
}



