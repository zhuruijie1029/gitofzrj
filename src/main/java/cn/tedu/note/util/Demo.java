package cn.tedu.note.util;

public class Demo {
	public static void main(String[] args) {
		//Girl friend = new Girl();
		//Girl friend2 = new Girl();
		//Girl g = Girl.girl;
		//Girl.girl = null;
		Girl g = Girl.getGirl();
		Girl g1 = Girl.getGirl();
	}
}
//饿汉式（立即式）
class Girl{
	//....
	private static Girl girl = new Girl();
	private Girl() {
		//...
	}
	public static Girl getGirl() {
		return girl;
	}
}
//懒惰式(懒汉式)
class Boy{
	//....
	private static Boy boy;
	private Boy() {
		//...
	}
	public synchronized static Boy getBoy() {
		if(boy==null){
			boy = new Boy();
		}
		return boy;
	}
}


