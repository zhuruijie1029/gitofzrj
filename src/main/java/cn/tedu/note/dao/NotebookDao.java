package cn.tedu.note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Notebook;

public interface NotebookDao {
	
	List<Map<String, Object>> 
		findNotebooksByUserId(String userId);
	
	int saveNotebook(Notebook book);

	Notebook findNotebookById(String notebookId);
}
