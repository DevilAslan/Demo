package cn.umbrella.mss.test.threadDemo2;

/**
 * 银行卡负责存钱
 * @author Administrator
 *
 */
public class Card implements Runnable{
    private String name;
    private Account account = new Account();
    
    public Card(String name,Account account) {
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
            account.addAccount(name,100);        }
    }
    
}