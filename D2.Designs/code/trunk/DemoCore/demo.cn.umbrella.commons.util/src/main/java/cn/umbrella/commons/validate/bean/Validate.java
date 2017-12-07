package cn.umbrella.commons.validate.bean;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZXL on 2015/11/9.
 */
public class Validate {
    private Rule rules;
    private Message messages;
    public Validate(){
        rules = new Rule();
        messages = new Message();
    }
    public void add(String field ,String rule,Object ruleValue,String ruleMessage){
        Map<String,Object> rules = this.rules.get(field);
        Map<String,Object>  messages = this.messages.get(field);
        if(rules==null){
            rules = new HashMap<String, Object>();
            messages = new HashMap<String, Object>();
            this.rules.put(field,rules);
            this.messages.put(field,messages);
        }

        rules.put(rule,ruleValue);
        messages.put(rule,ruleMessage);
    }

    class Rule extends HashMap<String,Map<String,Object>>{

    }
    class Message extends  HashMap<String,Map<String,Object>>{

    }

    public Rule getRules() {
        return rules;
    }

    public void setRules(Rule rules) {
        this.rules = rules;
    }

    public Message getMessages() {
        return messages;
    }

    public void setMessages(Message messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
