package cn.tedu.note.service.impl;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
@Service("userService")
public class UserServiceNewImpl implements UserService {
    @Resource
    private UserDao userDao;
    //登陆功能
    public User login(String name, String password) throws UserNameException, UPasswordException {

        if(name==null||name.trim().isEmpty()){
             throw new UserNameException("用户名不能为空");
        }
        if(password==null |name.trim().isEmpty()){
            throw new UPasswordException("密码不能为空");
        }
        User user=userDao.findUserByName(name);
        if(user==null){
            throw new UserNameException("用户名不存在");
        }
        String Md5Password =DigestUtils.md5Hex(password+"世界很大");
        if(password.equals(user.getPassword())){
            return user;
        }
        throw new UPasswordException("密码错误");
    }

    public User regist(String name, String nick, String password, String confirm) throws UserExistException, NameException, PasswordException {
        return null;
    }
}
