package cn.tedu.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.entity.Note;
import sun.misc.UUDecoder;

public class NoteDaoTestCase extends BaseTestCase {
	
	NoteDao dao;
	
	@Before
	public void initDao(){
		dao=ctx.getBean("noteDao", NoteDao.class);
	}
	
	@Test
	public void testFindNotesByNotebookId(){
		String notebookId="d0b0727f-a233-4a1f-8600-f49fc1f25bc9";
		List<Map<String, Object>> list=
			dao.findNotesByNotebookId(notebookId);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	@Test
	public void testFindNoteById(){
		String noteId="fed920a0-573c-46c8-ae4e-368397846efd";
		Note note = dao.findNoteById(noteId);
		System.out.println(note);
	}
	
	@Test
	public void testUpdateNote(){
		String noteId="fed920a0-573c-46c8-ae4e-368397846efd";
		String title = "Java";
		String body = "今天学习了JavaEE";
		long now = System.currentTimeMillis();
		
		Map<String, Object> map =
			new HashMap<String, Object>();
		map.put("id", noteId);
		map.put("title", title);
		map.put("body", body);
		//map.put("lastModifyTime", now);
		
		int n = dao.updateNote(map);
		System.out.println(n); 
		//检查更新结果
		Note note=dao.findNoteById(noteId);
		System.out.println(note);
	}
	
	@Test
	public void testAddNote(){
		String id=UUID.randomUUID().toString();
		String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		String notebookId="6d763ac9-dca3-42d7-a2a7-a08053095c08";
		String title = "Java Note";
		String body = "Hello World!";
		long now = System.currentTimeMillis();
		
		Note note = new Note(id, notebookId, userId, 
				"0", "0", title, body, now, now);
		int n = dao.addNote(note);
		System.out.println(n); 
	}

	@Test
	public void testDeleteNotes(){
		/*
 bb09798b-8f24-46ec-9c92-1534c2e1a9f5
 687e77a3-64e3-44ad-875b-83517a158196
 77f61ff8-e7a1-43ac-9740-5d60b8b15b76
		 */
		List<String> list=  
			new ArrayList<String>();
		list.add("bb09798b-8f24-46ec-9c92-1534c2e1a9f5");
		list.add("687e77a3-64e3-44ad-875b-83517a158196");
		list.add("77f61ff8-e7a1-43ac-9740-5d60b8b15b76");
		list.add("123");
		int n = dao.deleteNotes(list);
		System.out.println(n); 
	}
	
	@Test
	public void testDeleteNotesByParams(){
		//组织参数
		Map<String, Object> map=
			new HashMap<String, Object>();
		//添加参数statusId
		map.put("statusId", "1");
		//添加id列表
		List<String> list = 
			new ArrayList<String>();
		list.add("fsaf-as-df-asdf-as-df-dsa");
		list.add("ss19055-30e8-4cdc-bfac-97c6bad9518f");
		map.put("idList", list);
		//调用数据层方法
		int n=dao.deleteNotesByParams(map);
		System.out.println(n);
	}
	
	
	@Test
	public void testFindNotesByParams(){
		Map<String, Object> map =
			new HashMap<String, Object>();
		
		map.put("statusId", "1");
		map.put("key","a");
		map.put("name", "zhoujia");
		
		List<Map<String, Object>> list=
			dao.findNoteByParams(map);
		for (Map<String, Object> obj : list) {
			System.out.println(obj); 
		}
	}
	
	@Test
	public void testFindNotesByNotebookIdPaged(){
		String notebookId="6d763ac9-dca3-42d7-a2a7-a08053095c08";
		int start = 0;
		int size = 6;
		
		Map<String, Object> map =
			new HashMap<String, Object>();
		map.put("notebookId", notebookId);
		map.put("start", start);
		map.put("size", size);
		List<Map<String, Object>> list=
			dao.findNotesByNotebookIdPaged(map);
		for (Map<String, Object> m : list) {
			System.out.println(m); 
		}
	}
}	
















