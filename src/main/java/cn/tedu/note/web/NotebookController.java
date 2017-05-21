package cn.tedu.note.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.Notebook;
import cn.tedu.note.service.NotebookService;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/notebook")
public class NotebookController 
	extends BaseController{

	@Resource
	private NotebookService notebookService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>>
		list(String userId){
		List<Map<String, Object>> list=
			notebookService.listNotebooks(userId);
		return new JsonResult<List<Map<String,Object>>>(list);
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public JsonResult<Notebook> save(
			String name, String userId){
		Notebook book=notebookService.saveNotebook(name, userId);
		return new JsonResult<Notebook>(book);
	}

}











