package cn.tedu.note.service;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Note;

public interface NoteService {
	
	List<Map<String, Object>> listNotes(
			String notebookId)
			throws NotebookNotFoundException;

	
	Note loadNote(String noteId)
		throws NoteNotFoundException;
	
	boolean updateNote(String noteId, 
			String title, String body)
		throws NoteNotFoundException;

	Note addNote(String userId, 
		String notebookId, String title);

	int deleteNotes(String... ids);
	
	
	List<Map<String, Object>> listNotes(
			String notebookId,
			int page)
			throws NotebookNotFoundException;
}





