package cn.tedu.note.service;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Notebook;

public interface NotebookService {
	List<Map<String, Object>> listNotebooks(
			String userId) 
			throws UserNotFoundException; 
	
	Notebook saveNotebook(
			String name, String userId)
		throws UserNotFoundException; 
}
