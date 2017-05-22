package cn.tedu.note.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.User;
import cn.tedu.note.service.NameException;
import cn.tedu.note.service.PasswordException;
import cn.tedu.note.service.UserExistException;
import cn.tedu.note.service.UserService;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	//返回值: {state:0,data:{id...}}
	//返回值: {state:1,message:"用户名..."}
	public JsonResult<User> login(String name, 
			String password, 
			HttpServletRequest req){
		User user=
			userService.login(name, password);
		//将用户信息保存到Session中
		req.getSession().setAttribute(
				"loginUser", user);
		return new JsonResult<User>(user); 
	}
	/**
	 * 注册控制器
	 */
	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult<User> regist(
			String name, 
			String password, 
			String confirm, 
			String nick){
		User user=userService.regist(
			name, nick, password, confirm);
		return new JsonResult<User>(user);
	}
		
	//UserController
	@RequestMapping("/heartbeat.do")
	@ResponseBody
	//心跳检测控制器方法，目的保持session的新鲜
	public JsonResult<String> heartbeat(){
		//System.out.println("OK"); 
		return new JsonResult<String>("OK");
	}
	
	//UserController
	//produces="image/png" 用于设置 ContentType 头
	@RequestMapping(value="/img.do", 
			produces="image/png")
	@ResponseBody
	public byte[] img() throws IOException{
		//@ResponseBody 自动处理返回值
		//如果是Java Bean 处理为JSON
		//如果byte[] 就将byte数填充到返回
		//返回消息的 body中。
		//new 个照片发回去
		BufferedImage img=new BufferedImage(
			200, 56, 
			BufferedImage.TYPE_3BYTE_BGR);
		//将 img 进行编码 为 png 格式
		//FileOutputStream out = new ...;
		ByteArrayOutputStream out = 
				new ByteArrayOutputStream();
		ImageIO.write(img, "png", out);
		//拿到数组
		byte[] png=out.toByteArray();
		return png;
	}
	
	@RequestMapping(value="/img2.do", 
			produces="image/png")
	@ResponseBody
	public byte[] img2( 
		HttpServletResponse res)
		throws IOException {
		
		//设置 Content-Disposition 头，可以实现
		//下载文件功能，请参考 RFC2616 19.5.1章节
		// http://doc.tedu.cn/rfc/rfc2616.txt
		
		res.addHeader("Content-Disposition",
			"attachment; filename=\"girl.png\"");
		BufferedImage img=
			new BufferedImage(100, 50, 
			BufferedImage.TYPE_3BYTE_BGR);
		ByteArrayOutputStream out=
			new ByteArrayOutputStream();
		ImageIO.write(img,"png", out);
		byte[] png=out.toByteArray();
		return png;
	}
	
}

















