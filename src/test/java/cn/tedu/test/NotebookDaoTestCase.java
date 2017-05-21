package cn.tedu.test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.entity.Notebook;

public class NotebookDaoTestCase
	extends BaseTestCase{
	
	NotebookDao dao;
	
	@Before 
	public void initDao(){
		dao = ctx.getBean(
			"notebookDao", NotebookDao.class);
	}
	@Test
	public void testFindNotebooksByUserId(){
		String userId="ea09d9b1-ede7-4bd8-b43d-a546680df00b";
		List<Map<String, Object>> list=
			dao.findNotebooksByUserId(userId);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	@Test
	public void testSaveNotebook(){
		String id=UUID.randomUUID().toString();
		String userId="ea09d9b1-ede7-4bd8-b43d-a546680df00b";
		String typeId="0";
		String name = "Java";
		String desc="Java基础";
		Timestamp createTime= 
			new Timestamp(System.currentTimeMillis()); 
		Notebook notebook=new Notebook(id, userId, typeId, name, desc, createTime); 
		dao.saveNotebook(notebook);
	}

}






