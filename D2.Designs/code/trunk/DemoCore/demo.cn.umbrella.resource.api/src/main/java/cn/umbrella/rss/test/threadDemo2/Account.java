package cn.umbrella.rss.test.threadDemo2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 银行账户
 * @author Administrator
 *
 */
public class Account {
    private boolean flag=false;    // 默认flag 为false，要求必须先存款再取款
    private int count=0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    
    /**
     * 存钱
     * @param money
     */
    public void addAccount(String name,int money) {
        lock.lock();
        try {
            // flag 为true 表示可以存款，否则不可以存款
            if(flag) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                // 存钱
                count += money;
                System.out.println(name+"...存入："+money+"..."+Thread.currentThread().getName());
                SelectAccount(name);
                flag = true;
                condition.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }
    
    /**
     * 取钱
     * @param money
     */
    public void subAccount(String name,int money) {
        lock.lock();
        try {
            if(!flag) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
            // 先判断账户现在的余额是否够取钱金额
            if(count-money < 0){  
                System.out.println("账户余额不足！"); 
                return;  
            } 
                // 取钱
                count -= money;
                System.out.println(name+"...取出："+money+"..."+Thread.currentThread().getName());
                SelectAccount(name);
                flag = false;
                condition.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }
    
    /**
     * 查询余额
     */
    public void SelectAccount(String name) {
        System.out.println(name+"...余额："+count);
    }
}