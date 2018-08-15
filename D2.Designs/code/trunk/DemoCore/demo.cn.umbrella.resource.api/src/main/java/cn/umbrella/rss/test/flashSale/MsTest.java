package cn.umbrella.rss.test.flashSale;


//import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext; 
import org.springframework.data.redis.core.RedisTemplate;
 

public class MsTest {
//	/demo.cn.umbrella.resource.api/src/main/resources/spring/spring-redis.xml
//	/demo.cn.umbrella.resource.api/src/main/java/cn/umbrella/mss/test/flashSale/MsTest.java
    static ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("src/main/resourcesres/spring-redis.xml");
    

     @SuppressWarnings("unchecked")
	public static void main(String[] args) {
          RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)appCtx.getBean("redisTemplate",RedisTemplate.class);
          
          
          //RedisUtil redisUtil=(RedisUtil) appCtx.getBean("redisUtil");  
          System.out.println("开始");
          
            MsService service = new MsService();
            
            for (int i = 0; i < 100; i++) {
                ThreadB threadA = new ThreadB(service, redisTemplate, "MSKEY");
                threadA.start();
               
            }
           
    }
}