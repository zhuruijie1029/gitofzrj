package cn.tedu.note.entity;

import java.io.Serializable;

public class PUser implements Serializable {
	private static final long serialVersionUID = -8016188877003168896L;
	
	private Integer id;
	private String name;
	private String nick;
	
	public PUser() {
	}

	public PUser(String name, String nick) {
		super();
		this.name = name;
		this.nick = nick;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "PUser [id=" + id + ", name=" + name + ", nick=" + nick + "]";
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
		PUser other = (PUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
