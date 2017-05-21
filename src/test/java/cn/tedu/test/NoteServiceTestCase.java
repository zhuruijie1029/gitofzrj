package cn.tedu.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.entity.Note;
import cn.tedu.note.entity.Notebook;
import cn.tedu.note.service.NoteService;

public class NoteServiceTestCase 
	extends BaseTestCase {
	
	NoteService service;
	
	@Before
	public void initService(){
		this.service = ctx.getBean(
			"noteService",NoteService.class);
	}
	@Test
	public void testListNotes(){
		String notebookId="d0b0727f-a233-4a1f-8600-f49fc1f25bc9";
		List<Map<String, Object>> list=
			service.listNotes(notebookId);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}

	}
	
	@Test
	public void testLoadNote(){
		String noteId="fed920a0-573c-46c8-ae4e-368397846efd";
		Note note = service.loadNote(noteId);
		System.out.println(note);

	}
	
	/* NoteServiceTestCase */
	@Test
	public void testUpdateNote(){
		String noteId="fed920a0-573c-46c8-ae4e-368397846efd";
		String title = "Java ABC";
		String body = "今天Hello World";
		boolean b =service.updateNote(noteId, title, body);
		System.out.println(b);
		Note n = service.loadNote(noteId);
		System.out.println(n); 
	}
	
	@Test
	public void testAddNote(){
		String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		String notebookId="6d763ac9-dca3-42d7-a2a7-a08053095c08";
		String title = "Java Hello";
		
		Note note = service.addNote(userId, notebookId, title);
		System.out.println(note);
	}
	
	@Test
	public void testDeleteNotes(){
		/* 
7d4c538f-e4b5-4ac0-8a38-b1aa21c40cda
9e8d711a-6dcf-4507-95ed-e70783ae3838
ba7e3681-2a11-4f8d-ba30-6a97369e92ab */
		String id1="7d4c538f-e4b5-4ac0-8a38-b1aa21c40cda";
		String id2="9e8d711a-6dcf-4507-95ed-e70783ae3838";
		String id3="ba7e3681-2a11-4f8d-ba30-6a97369e92ab";
		//String id4="123";
		// String... 变长参数，编译过后是 
		// new String[]{id1,id2,id3,id4}
		// String... 只能用于最后一个参数
		int n = service.deleteNotes(
			id1, id2, id3);
		System.out.println(n); 
	}
	
	
	@Test
	public void testListNotesPaged(){
		String notebookId="6d763ac9-dca3-42d7-a2a7-a08053095c08";
		int page = 2;
		List<Map<String, Object>> list=
			service.listNotes(notebookId, page);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
}







