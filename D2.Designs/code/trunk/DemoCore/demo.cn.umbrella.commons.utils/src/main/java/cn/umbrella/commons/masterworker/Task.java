package cn.umbrella.commons.masterworker;
/**
 * Type Name : Task
 * Description :  任务基
 * Author : 林吉前
 * Create Date ： 2017年6月29日
 * Version ：
 *
 * History: (Version) Author Date Annotation
 */
public abstract class Task {

    private String taskId;

    public Task(String id){
    	this.taskId = id;
    }
    
    public String getTaskId(){
        return this.taskId;
    }

}
