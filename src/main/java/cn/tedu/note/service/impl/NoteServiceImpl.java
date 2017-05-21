package cn.tedu.note.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.Note;
import cn.tedu.note.entity.Notebook;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.NoteNotFoundException;
import cn.tedu.note.service.NoteService;
import cn.tedu.note.service.NotebookNotFoundException;
import cn.tedu.note.service.UserNotFoundException;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteDao noteDao;
	
	@Resource 
	private NotebookDao notebookDao;
	
	@Resource
	private UserDao userDao;
	
	@Transactional(readOnly=true)
	public List<Map<String, Object>> listNotes(
			String notebookId) throws NotebookNotFoundException {
		if(notebookId==null||notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("notebookId不能空");
		}
		Notebook book = notebookDao.findNotebookById(notebookId);
		if(book == null){
			throw new NotebookNotFoundException("notebookId不存在");
		}
		
		return noteDao.findNotesByNotebookId(notebookId);
	}
	
	@Transactional
	public Note loadNote(String noteId) 
			throws NoteNotFoundException {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new NoteNotFoundException("ID不能空");
		}
		Note note = noteDao.findNoteById(noteId);
		if(note==null){
			throw new NoteNotFoundException("ID错误了");
		}
		return note;
	}
	
	@Transactional
	public boolean updateNote(String noteId, 
			String title, String body) 
			throws NoteNotFoundException {
		if(noteId==null||noteId.trim().isEmpty()){
			throw new NoteNotFoundException("ID不能空");
		}
		Note note = noteDao.findNoteById(noteId);
		if(note==null){
			throw new NoteNotFoundException("ID错");
		}
		//创建Map, 封装更新参数
		//如 title 是 null 则不更新title
		Map<String, Object> params = 
			new HashMap<String, Object>();
		if(title!=null &&
				! title.trim().isEmpty()){
			params.put("title", title.trim());	
		}
		//笔记本内容为null, 不更新笔记内容
		if(body!=null){
			if(!body.equals(note.getBody())){
				params.put("body", body.trim());
			}
		}
		//如果title 和 body 都没有,则无需更新?
		if(params.isEmpty()){
			return false;//更新失败
		}
		//添加必须参数
		params.put("id", noteId);
		params.put("lastModifyTime", 
			System.currentTimeMillis());
		//更新数据
		int n =noteDao.updateNote(params);
		return n==1;
//		if(n==1){
//			return true;//更新成功
//		}
//		return false;//更新失败
	}
	
	@Transactional
	public Note addNote(String userId,
		String notebookId, String title) {
		if(notebookId==null||notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("notebookId不能空");
		}
		Notebook book = notebookDao.findNotebookById(notebookId);
		if(book == null){
			throw new NotebookNotFoundException("notebookId不存在");
		}
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("userId不能空");
		}
		User user = userDao.findUserById(userId);
		if(user == null){
			throw new UserNotFoundException("userId不存在");
		}
		if(title==null || title.trim().isEmpty()){
			throw new RuntimeException("title不存在");
		}
		String id = UUID.randomUUID().toString();
		title = title.trim();
		String body = "";
		String typeId="0";
		String statusId="0";
		long now = System.currentTimeMillis();
		Note note = new Note(id, notebookId, userId, statusId, typeId, title, body, now, now);
		int n = noteDao.addNote(note);
		if( n == 1){
			
			return note;
		}
		throw new RuntimeException("保存失败!");

	}
//	@Transactional
//	public void test(){
//		deleteNotes();
//		addNote(userId, notebookId, title);
//	}
	
	@Transactional
	public int deleteNotes(String... ids) {
		//String... 就是 String[] 
//		for(String id: ids){
//			int n = noteDao.deleteNote(id);
//			if(n!=1){
//				throw new NoteNotFoundException(id);
//			}
//		}
//		return ids.length;
		int n = noteDao.deleteNotes(
				Arrays.asList(ids));
		if(n!=ids.length){
			throw new NoteNotFoundException("id错");
		}
		return n;
	}
	
	@Transactional(readOnly=true)
	public List<Map<String, Object>> 
		listNotes(String notebookId, int page)
		throws NotebookNotFoundException {
		
		if(notebookId==null || notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("ID NULL");
		}
		Notebook book=notebookDao.findNotebookById(notebookId);
		if(book==null){
			throw new NotebookNotFoundException("ID错误");
		}
		//计算分页参数
		int size = 6;
		int start = page * size;
		//检查start是否有效
		if(start < 0){
			start = 0;
		}
		int max = 
			noteDao.countNotes(notebookId);
		if(start>=max){
			return new 
			ArrayList<Map<String,Object>>();
		}
		//拼凑参数 
		Map<String, Object> map=
			new HashMap<String, Object>();
		map.put("notebookId", notebookId);
		map.put("start", start);
		map.put("size", size);
		//分页查询
		return noteDao.findNotesByNotebookIdPaged(map);
	}
}











