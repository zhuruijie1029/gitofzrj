package cn.tedu.note.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.Note;
import cn.tedu.note.service.NoteService;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/note")
public class NoteController 
	extends BaseController{

	@Resource
	private NoteService noteService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>>
		list(String notebookId){
		List<Map<String, Object>> list=
			noteService.listNotes(notebookId);
		return new JsonResult<List<Map<String,Object>>>(list);
	}
	
	
	@RequestMapping("/load.do")
	@ResponseBody
	public JsonResult<Note> load(String noteId){
		Note note=noteService.loadNote(noteId);
		return new JsonResult<Note>(note);
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public JsonResult<Boolean> update(
			String noteId,
			String title, 
			String body){
		boolean b =noteService.updateNote(
				noteId, title, body);
		return new JsonResult<Boolean>(b);
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult<Note> add(
			String userId,
			String notebookId, 
			String title){
		Note note =noteService.addNote(
				userId, notebookId, title);
		return new JsonResult<Note>(note);
	}
	
	
	@RequestMapping("/list2.do")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>>
		list2(String notebookId, int page){
		List<Map<String, Object>> list=
			noteService.listNotes(notebookId, page);
		return new JsonResult<List<Map<String,Object>>>(list);
	}

	
}











