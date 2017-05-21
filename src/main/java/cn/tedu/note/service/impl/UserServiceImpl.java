package cn.tedu.note.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.NameException;
import cn.tedu.note.service.PasswordException;
import cn.tedu.note.service.UserExistException;
import cn.tedu.note.service.UserService;

@Service("userService")
public class UserServiceImpl 
	implements UserService{

	@Resource
	private UserDao userDao;
	
	public User login(String name, 
			String password) 
			throws NameException, 
			PasswordException {
		
		System.out.println("login");
 		
		//根据用户输入的用户名和密码检查是否登录
		if(name==null || name.trim().isEmpty()){
			throw new NameException("用户名不能空");
		}
		if(password==null || password.trim().isEmpty()){
			throw new PasswordException("密码不能空");
		}
		//获取用户信息, 比较密码
		User user=userDao.findUserByName(name);
		if(user==null){
			throw new NameException("用户不存在");
		}
		String md5 = DigestUtils.md5Hex(
				password+"世界你好");
		
		//String s = null;
		//s.charAt(0);
		
		if(md5.equals(user.getPassword())){
			return user;//登录成功
		}
		throw new PasswordException("密码错!");
	}
	
	public User regist(String name,String nick,
			String password, String confirm)
			throws UserExistException, NameException, PasswordException {
		String reg = "^\\w{3,10}$";
		if(name==null||name.trim().isEmpty()){
			throw new NameException("不能空");
		}
		if(! name.matches(reg)){
			throw new NameException("3~10字符");
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("不能空");
		}
		if(! password.matches(reg)){
			throw new PasswordException("3-10字符");
		}
		if(! password.equals(confirm)){
			throw new PasswordException("确认密码不一致");
		}
		//检查用户是否已经注册!
		User user = userDao.findUserByName(name);
		if(user!=null){
			throw new UserExistException("已经注册");
		}
		if(nick==null || nick.trim().isEmpty()){
			nick = name;
		}
		//检查了所有参数, 保存数据
		String id = UUID.randomUUID().toString();
		String token = "";
		String md5 = 
			DigestUtils.md5Hex(password+"世界你好");
		user = new User(id,name,md5,token,nick);
		int i = userDao.saveUser(user);
		if(i!=1){
			throw new RuntimeException("保存失败");
		}
		return user;
	}
}






