package cn.umbrella.commons.utils.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
/**
 * Master
 * @author ThingLin
 *
 * @param <T> 任务
 * @param <V> 结果
 */
public abstract class Master<T extends cn.umbrella.commons.utils.masterworker.Task,V> {

    /* 任务队列 */
    protected ConcurrentLinkedQueue<T> tasks = new ConcurrentLinkedQueue<T>();

    /* Worker管理 */
    protected HashMap<Integer,Thread> workers = new HashMap<Integer,Thread>();

    /* 结果集 */
    protected ConcurrentHashMap<String,V> results = new ConcurrentHashMap<String,V>();

    /* 线程监听 */
    protected CountDownLatch latch;
    
    public Master(Worker<T,V> worker,int workerCount){
    	this.latch = new CountDownLatch(workerCount);
        worker.setTasks(this.tasks);
        worker.setResults(this.results);
        worker.setLatch(this.latch);
        for(int i=0;i<workerCount;i++){
            this.workers.put(i, new Thread(worker,String.format("%s,%d","worker",i)));
        }
    }
    
    public CountDownLatch getLatch(){
    	return this.latch;
    }

    public void submit(T task){
        tasks.add(task);
    }

    public void excute(){
        for(Map.Entry<Integer,Thread> item : workers.entrySet()){
            item.getValue().start();
    	}
    }

    /**
     * 
     * @return 线程全部执行完成时任务执行完true
     */
    public boolean isComplete(){
        for(Map.Entry<Integer,Thread> item : workers.entrySet()){
            if(item.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    /**
     * 结果集计算
     * @return
     */
    public abstract V result();

    public ConcurrentHashMap<String,V> getResults(){
    	return this.results;
    }
    
}
