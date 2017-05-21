package cn.tedu.note.service;

import cn.tedu.note.entity.User;

public interface UserService {
	/**
	 * 登录功能 
	 * @param name 用户输入的用户名
	 * @param password 用户输入的密码
	 * @return 如果登录成功返回用户信息
	 * @throws NameException 用户名错误
	 * @throws PasswordException 密码错误
	 */
	/*
	User login(String name, String password)
		throws NameException, PasswordException;
	*/
	User login(String name,String password) throws UserNameException,UPasswordException;
	User regist(String name, String nick,
		String password, String confirm )
		throws UserExistException, 
		NameException, PasswordException;

}


