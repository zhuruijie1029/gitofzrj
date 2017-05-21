package cn.tedu.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.entity.Notebook;
import cn.tedu.note.service.NotebookService;

public class NotebookServiceTestCase 
	extends BaseTestCase {
	
	NotebookService service;
	
	@Before
	public void initService(){
		this.service = ctx.getBean(
			"notebookService",NotebookService.class);
	}
	@Test
	public void testListNotebooks(){
		String userId="ea09d9b1-ede7-4bd8-b43d-a546680df00b";
		List<Map<String, Object>> list=
			service.listNotebooks(userId);
		for (Map<String, Object> map : list) {
			System.out.println(map); 
		}
	}
	
	@Test
	public void testSaveNotebook(){
		String name="测试笔记本";
		String userId="ea09d9b1-ede7-4bd8-b43d-a546680df00b";
		Notebook book = service.saveNotebook(name, userId);
		System.out.println(book);
	}
}



