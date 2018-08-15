package cn.umbrella.rss.test.threadDemo2;


public class ThreadDemo2 {
    public static void main(String[] args) {
        
        // 开个银行帐号
        Account acount = new Account();
        
        // 开银行帐号之后银行给张银行卡
        Card card1 = new Card("card1",acount);
        Card card2 = new Card("card2",acount);
        Card card3 = new Card("card3",acount);
        
        // 开银行帐号之后银行给张存折
        Paper paper1 = new Paper("paper1",acount);
        Paper paper2 = new Paper("paper2",acount);
        
        // 创建三个银行卡
        Thread thread1 = new Thread(card1,"card1");
        Thread thread2 = new Thread(card2,"card2");
        Thread thread3 = new Thread(card3,"card3");
        // 创建两个存折
        Thread thread4 = new Thread(paper1,"paper1");
        Thread thread5 = new Thread(paper2,"paper2");
        
        thread1.start();
        thread2.start();
        thread3.start();
        
        thread4.start();
        thread5.start();
    }
}