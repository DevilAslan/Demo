import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		String f_a = "yyyy-MM-dd HH:mm:ss";
		String f_b = "yyyy-MM-dd";
//		SimpleDateFormat df = new SimpleDateFormat(f_a);//设置日期格式
//		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
//		System.out.println();
		
		DateFormat df = new SimpleDateFormat(f_b);  
//        java.util.Date date = df.parse("2014-01-11 01:37:00");  
        java.util.Date date = df.parse("1970-01-01"); 
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        long timestamp = cal.getTimeInMillis();  
        System.out.println(timestamp/1000);  
        
        String str = "PNG";
		if("gif".equalsIgnoreCase(str) || "jpg".equalsIgnoreCase(str) || "png".equalsIgnoreCase(str) || "bmp".equalsIgnoreCase(str)){
			System.out.println("true");
		}
	}

}
