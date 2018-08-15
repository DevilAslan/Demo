package cn.umbrella.rss.test.threadDemo2;

/**
 * 存折负责取钱
 * @author Administrator
 *
 */
public class Paper implements Runnable{
    private String name;
    private Account account = new Account();
    
    public Paper(String name,Account account) {
        this.account = account;
        this.name = name;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.subAccount(name,50);
        }
        
    }

}