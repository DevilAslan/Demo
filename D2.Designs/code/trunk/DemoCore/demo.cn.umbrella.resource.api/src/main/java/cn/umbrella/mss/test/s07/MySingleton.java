package cn.umbrella.mss.test.s07;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class MySingleton implements Serializable {
	 
	private static final long serialVersionUID = 1L;

	//内部类
	private static class MySingletonHandler{
		private static MySingleton instance = new MySingleton();
	} 
	
	private MySingleton(){}
	 
	public static MySingleton getInstance() { 
		return MySingletonHandler.instance;
	}
	
	//该方法在反序列化时会被调用，该方法不是接口定义的方法，有点儿约定俗成的感觉
	protected Object readResolve() throws ObjectStreamException {
		System.out.println("调用了readResolve方法！");
		return MySingletonHandler.instance; 
	}
}


