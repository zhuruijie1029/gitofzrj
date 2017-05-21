package cn.tedu.note.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect //切面儿
public class DemoAspect {
	
	//声明通知和切入点：在login方法之前插入Hello World 
	//@Before: 通知, 切入点：bean(userService)
	// 在userService的全部方法之前执行 test
	//@Before("bean(userService)")
	//@AfterReturning("bean(userService)")
	public void test(){
		System.out.println("Hello World!"); 
	}
}
