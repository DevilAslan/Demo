package cn.umbrella.commons.utils.masterworker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
/**
 * Worker
 * @author ThingLin
 *
 * @param <T> 任务
 * @param <V> 结果
 */
public abstract class Worker<T extends cn.umbrella.commons.utils.masterworker.Task,V> implements Runnable {

    /* 任务队列 */
    protected ConcurrentLinkedQueue<T> tasks;

    /* 结果集 */
    protected ConcurrentHashMap<String,V> results;

    /* 线程监听 */
    protected CountDownLatch latch;
    
    @Override
    public void run() {
        while(true){
            T task = this.tasks.poll(); //取出任务
            if(null == task){ //没有任务可执行跳出循环
                break;
            }
            V v = dispose(task);
            if(null != this.results && null != task){
            	this.results.put(task.getTaskId(), v);
            }
        }
        this.latch.countDown();
    }

    public void setTasks(ConcurrentLinkedQueue<T> tasks){
        this.tasks = tasks;
    }

    public void setResults(ConcurrentHashMap<String,V> results){
        this.results = results;
    }
    
    public CountDownLatch getLatch() {
		return latch;
	}

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	/**
     * 处理任务
     * @param task
     * @return
     */
    public abstract V dispose(T task);

}
