package cn.tedu.note.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post implements Serializable{
	private static final long serialVersionUID = 8958411428421018244L;

	private Integer id;
	//发贴人
	private PUser user;
	//当前的回复
	private List<Comment> comments = 
			new ArrayList<Comment>();
	//发帖内容
	private String body;
	private Date date;
		
	public Post() {
	}

	public Post(Integer id, PUser user, String body, Date date) {
		super();
		this.id = id;
		this.user = user;
		this.body = body;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", user=" + user + ", comments=" + comments + ", body=" + body + ", date=" + date
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
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
