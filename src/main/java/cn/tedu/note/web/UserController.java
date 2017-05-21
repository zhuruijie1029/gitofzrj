package cn.tedu.note.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.note.entity.User;
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
	
	
	//UserController
	
	@RequestMapping(value="/download.do",
			produces="application/vnd.ms-excel")
	@ResponseBody
	public byte[] download(HttpServletResponse res)
		throws IOException{
		
		res.addHeader("Content-Disposition",
				"attachment; filename=\"hello.xls\"");
		//res.setContentType("application/vnd.ms-excel");
		//创建Excel工作薄对象
		HSSFWorkbook workbook=new HSSFWorkbook();
		//在工作簿中创建工作表
		HSSFSheet sheet=workbook.createSheet();
		//创建一行
		HSSFRow row=sheet.createRow(0);//行号
		//创建一个格子
		HSSFCell cell=row.createCell(0);//列号
		//向格子填充数据
		cell.setCellValue("Hello World!");
		
		//将Excel对象序列化为 byte[]数据
		ByteArrayOutputStream out=
			new ByteArrayOutputStream();
		workbook.write(out); 
		workbook.close();
		
		byte[] buf=out.toByteArray();
		return buf;
	}

	//UserController
	@RequestMapping(value="/upload.do",
			method=RequestMethod.POST)
	@ResponseBody
	public String upload(
		MultipartFile photo,
		String name) throws IOException{
		
		//MultipartFile 是Spring提供了API
		//封装了上载数据的信息：原始文件名，
		//文件的类型（mime ContentType）
		//文件的数据：byte[] 或者 输入流
		//上载时控件 name 属性的值
		
		//获取原始文件名
		String filename=
			photo.getOriginalFilename();
		//获取文件类型
		String type=
			photo.getContentType();
		//文件的数据
		byte[] data=
			photo.getBytes();
		
		File file = new File("d:/", filename);
		FileOutputStream out= 
			new FileOutputStream(file);
		out.write(data);
		out.close();
		
		return name+"Success!";
	}
	
	
}

















