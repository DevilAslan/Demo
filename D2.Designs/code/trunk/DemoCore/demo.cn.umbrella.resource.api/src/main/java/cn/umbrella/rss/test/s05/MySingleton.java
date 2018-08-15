package cn.umbrella.rss.test.s05;

public class MySingleton {
	
	//使用volatile关键字保其可见性
	volatile private static MySingleton instance = null;
	
	private MySingleton(){}
	 
	public static MySingleton getInstance() {
		try {  
			if(instance != null){//懒汉式 
				
			}else{
				//创建实例之前可能会有一些准备性的耗时工作 
				Thread.sleep(300);
				synchronized (MySingleton.class) {
					if(instance == null){//二次检查
						instance = new MySingleton();
					}
				}
			} 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		return instance;
	}
}

