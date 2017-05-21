package cn.tedu.note.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable{
	private static final long serialVersionUID = 9005740179566619541L;
	
	private Integer id;
	private Integer userId;
	private Integer postId;
	private String body;
	private Date date;
	
	public Comment() {
	}

	public Comment(Integer userId, Integer postId, String body, Date date) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.body = body;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", postId=" + postId + ", body=" + body + ", date=" + date
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
